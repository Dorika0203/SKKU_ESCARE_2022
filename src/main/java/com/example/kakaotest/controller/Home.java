package com.example.kakaotest.controller;


import com.example.kakaotest.component.SessionAttribute;

import com.example.kakaotest.component.SessionControl;
import com.example.kakaotest.component.mail.MailDto;
import com.example.kakaotest.component.mail.MailService;
import com.example.kakaotest.model.*;
import com.example.kakaotest.repository.*;
import com.fortanix.sdkms.v1.ApiClient;
import com.fortanix.sdkms.v1.ApiException;
import com.fortanix.sdkms.v1.JSON;
import com.fortanix.sdkms.v1.model.Group;
import com.fortanix.sdkms.v1.model.KeyObject;
import com.fortanix.sdkms.v1.model.Plugin;
import com.fortanix.sdkms.v1.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

import static com.example.kakaotest.fortanix.FortanixRestApi.*;
import static org.apache.commons.codec.digest.DigestUtils.sha256;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
@Controller
@AllArgsConstructor
public class Home {


    AdminRepository adminRepository;
    Group2EmailRepo group2EmailRepo;
    GroupUUIDRepo groupUUIDRepo;
    User2EmailRepo user2EmailRepo;
    UserInfoRepo userInfoRepo;

    private final JobLauncher jobLauncher;
    private final Job simpleJob;
    private final Job updateGroupJob;
    private final JavaMailSender javaMailSender;





    // ------------------------------------------------------------------ login ---------------------------------------------- //
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(HttpSession session)
    {
        if(SessionAttribute.isSessionAvailable(session)) return "redirect:/config";
        return "login";
    }

    @ResponseBody
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public int loginRequest(String id, String pw, HttpSession session) throws NoSuchAlgorithmException
    {
        log.info(id + " " + pw);
        if(id.length()<1) return 1; // id short
        if(pw.length()<1) return 2; // pw short
        Optional<AdminModel> op = adminRepository.findById(id);
        if(op.isEmpty()) return 3; // no such id exist
        if(!Arrays.equals(op.get().getPw(), sha256(pw.getBytes()))) return 4; // pw wrong
        SessionAttribute.setSessionUserID(id, session);
        session.setMaxInactiveInterval(3600); // 1hour
        return 0;
    }

    // ------------------------------------------------------------------ export ---------------------------------------------- //
    //Display exportable keys with their groups, which have KCV.
    @GetMapping(path="/export")
    public String export(Model model, HttpSession session) throws ApiException {

        if(!SessionAttribute.isSessionAvailable(session)) return "redirect:/login";
        //Verify client to access groups and keys
        String server = "https://sdkms.fortanix.com";
        String username = "ysoh1205@g.skku.edu";
        String password = "fortanixskku@";
        ApiClient client;
        client = generateFortanixSDKMSClientAndVerify(server, username, password);

        //Get all keys of client
        List<KeyObject> keyObjects = getSecurityObjects(client);
        List<Group> groups = new ArrayList<>();

        String[] TYPES = new String[] {"AES", "DES", "DES3"};
        JSONArray groupsName = new JSONArray();

        //Get all groups of client
        for(int i=0; i<keyObjects.size(); i++){
            Group group = getGroupsById(client, keyObjects.get(i).getGroupId());
            groups.add(group);
            if(groups.stream().distinct().count()!=groups.size()) groups.remove(groups.size()-1);
        }
        for(int i=0; i<groups.size(); i++){
            groupsName.put(groups.get(i).getName());
        }

        /*
            Construct JSON object of group-key pair
            {
                "group1": ["Key1 of group1", "Key2 of group1", ...],
                "group2": ["Key1 of group2", "Key2 of group2", ...],
                ...
            }
         */
        JSONObject groupsArr = new JSONObject();
        for(int i=0; i<groups.size(); i++) {
            String groupId = groups.get(i).getGroupId();
            JSONArray objectsArr = new JSONArray();
            for (int j = 0; j < keyObjects.size(); j++) {
                KeyObject object = keyObjects.get(j);
                if (keyObjects.get(j).getGroupId().equals(groupId)) {
                    //Keys must be exportable, and have KCV
                    if(!Arrays.asList(TYPES).contains(object.getObjType().getValue()) || object.getNeverExportable()) continue;
                    objectsArr.put(keyObjects.get(j).getName());
                }
            }
            groupsArr.put(groups.get(i).getName(), objectsArr);
        }

        //Send groups name and JSON object to component.jsp file.
        model.addAttribute("group-object_Pair", groupsArr);
        model.addAttribute("groups", groupsName);
        return "component";
    }

    @ResponseBody
    @PostMapping(path="export")
    public int exportRequest(String groupName, String sobjectName) throws ApiException {

        //Classes for sending mail
        List<MailDto> mailObjects = new ArrayList();
        MailService mailService = new MailService(javaMailSender);
        
        //Verify client to access groups and keys        
        String server = "https://sdkms.fortanix.com";
        String username = "ysoh1205@g.skku.edu";
        String password = "fortanixskku@";
        ApiClient client;

        client = generateFortanixSDKMSClientAndVerify(server, username, password);
        
        /*
            Construct plugin input format by JSON object
            {
                "objectname" : sobjectName
            }
        */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("objectname", sobjectName);
        String jsonString = jsonObject.toString();

        //Get group which the key belongs to.
        String groupId = getSecurityObjectByName(client, sobjectName).getGroupId();
        Group group = getGroupsById(client, groupId);

        //Get plugin by using group name : "Key components export for 'group1'"
        String pluginName = "Key components export for " + groupName;
        Plugin plugin = getPluginByName(client, groupId, pluginName);

        //If there is no plugin,
        if(plugin==null){
            return 1;
        }
        
        //Get response by String
        String pluginId = plugin.getPluginId();
        String responseStr = invokePlugin(client, pluginId, jsonString);
        System.out.println(responseStr);
        
        //If there are no custodians in this group,
        if(responseStr.equals("\"no custodians in this group\"")){
            return 2;
        }

        //Convert response to JSON object
        JSONObject response = new JSONObject(responseStr);
        int custodianNum = ((JSONObject)response.get("custodians")).length();
        
        //Get custodians email
        List<String> mails = new ArrayList<>();
        for(int i=0; i<custodianNum; i++) {
            //Get email by there fortanix sdkms user id
            String userId = ((JSONObject)response.get("custodians")).get("user " + i).toString();
            mails.add(getUserEmail(client, userId));
        }

        //Construct MailDto class variables
        for(int i=0; i<custodianNum; i++) {
            int tmp = i+1;
            /*
                format:
                    Title: Components and KCV of exported Security Object : 'security object name'
                    Content: main KCV : 538E7B,
                             component 1 : {
                                  component: E68F4799728F67AA,
                                  kcv: C21F10
                             }
            */
            mailObjects.add(new MailDto(
                    mails.get(i),
                    String.format("Components and KCV of exported Security Object : %s",sobjectName),
                    String.format("main KCV : %s,\n" +
                            "component %d : {\n" +
                            "   component : %s,\n" +
                            "   kcv : %s\n" +
                            "}", response.get("mainKCV"), tmp, ((JSONObject)((JSONArray)response.get("components")).get(i)).get("component"), ((JSONObject)((JSONArray)response.get("components")).get(i)).get("kcv"))
            ));
        }

        //Sending mails to custodians
        for(int i=0; i<custodianNum; i++) {
            mailService.mailSend(mailObjects.get(i));
        }
        return 0;
    }

    // ------------------------------------------------------------------ config ---------------------------------------------- //
    @GetMapping(path="/config")
    public String config(HttpSession session, Model model){
        if(!SessionAttribute.isSessionAvailable(session)) return "redirect:/login";

        List<UserInfo> userInfoList = userInfoRepo.findAll();
        List<User2Email> user2EmailList = user2EmailRepo.findAll();
        List<GroupUUID> groupUUIDList = groupUUIDRepo.findAll();
        List<Group2Email> group2EmailList = group2EmailRepo.findAll();

        // users
        JSONArray userArray = new JSONArray();
        for (UserInfo userinfo: userInfoList) {
            JSONObject tmp = new JSONObject();
            tmp.put("user_name", userinfo.getUserName());
            tmp.put("user_code", userinfo.getUserCode());
            userArray.put(tmp);
        }
        // groupUUID
        JSONArray groupArray = new JSONArray();
        for (GroupUUID guid : groupUUIDList) {
            JSONObject tmp = new JSONObject();
            tmp.put("group_name", guid.getGroup_name());
            tmp.put("group_guid", guid.getGuid());
            groupArray.put(tmp);
        }
        // emails
        JSONArray u2eArray = new JSONArray();
        for (User2Email user2Email: user2EmailList) {
            JSONObject tmp = new JSONObject();
            tmp.put("user_code", user2Email.getUserCode().getUserCode());
            tmp.put("email_addr", user2Email.getEmail());
            u2eArray.put(tmp);
        }
        // group2Email
        JSONArray g2eArray = new JSONArray();
        for (Group2Email g2e: group2EmailList) {
            JSONObject tmp = new JSONObject();
            tmp.put("group_guid", g2e.getGuid().getGuid());
            tmp.put("email_addr", g2e.getEmail().getEmail());
            g2eArray.put(tmp);
        }

        model.addAttribute("groupUUID", groupArray);
        model.addAttribute("users", userArray);
        model.addAttribute("emails", u2eArray);
        model.addAttribute("group2Email", g2eArray);
        return "config";
    }

    @RequestMapping(path = "/config/user-add",method = RequestMethod.POST)
    @ResponseBody
    public int userAdd(String id, HttpSession session) {
        if(!SessionAttribute.isSessionAvailable(session)) return 2;
        if(userInfoRepo.findByUserName(id).isPresent()) return 1;
        UserInfo newUser = new UserInfo();
        newUser.setUserName(id);
        userInfoRepo.save(newUser);
        userInfoRepo.flush();
        return 0;
    }

    @RequestMapping(path = "/config/user-delete",method = RequestMethod.POST)
    @ResponseBody
    public int userDelete(long id, HttpSession session) {
        if(!SessionAttribute.isSessionAvailable(session)) return 2;
        Optional<UserInfo> findUser = userInfoRepo.findById(id);
        if(findUser.isEmpty()) return 1;
        UserInfo foundUser = findUser.get();

        // should remove referenced DB entities...v
        List<User2Email> foundU2E = user2EmailRepo.findAllByUserCode(foundUser);
        for (User2Email u2e: foundU2E) {
            List<Group2Email> removeTarget = group2EmailRepo.findAllByEmail(u2e);
            for (Group2Email g2e: removeTarget) {
                group2EmailRepo.delete(g2e);
            }
            user2EmailRepo.delete(u2e);
        }

        userInfoRepo.delete(foundUser);
        return 0;
    }

    @RequestMapping(path = "/config/u2e-add",method = RequestMethod.POST)
    @ResponseBody
    public int u2eAdd(long id, String email, HttpSession session) {
        if(!SessionAttribute.isSessionAvailable(session)) return 3;
        Optional<UserInfo> found = userInfoRepo.findById(id);
        if(found.isEmpty()) return 1;
        else if(user2EmailRepo.findById(email).isPresent()) return 2;
        User2Email newU2E = new User2Email();
        newU2E.setEmail(email);
        newU2E.setUserCode(found.get());
        user2EmailRepo.saveAndFlush(newU2E);
        return 0;
    }

    @RequestMapping(path = "/config/u2e-delete",method = RequestMethod.POST)
    @ResponseBody
    public int u2eDelete(long id, String email, HttpSession session) {
        if(!SessionAttribute.isSessionAvailable(session)) return 4;
        Optional<UserInfo> found1 = userInfoRepo.findById(id);
        if(found1.isEmpty()) return 1;
        Optional<User2Email> found2 = user2EmailRepo.findById(email);
        if(found2.isEmpty()) return 2;
        User2Email found = found2.get();

        // WTF
        if(found.getUserCode().getUserCode() != id) return 3;

        // should remove referenced DB entities...
        List<Group2Email> removeTarget = group2EmailRepo.findAllByEmail(found);
        for(Group2Email g2e: removeTarget) { group2EmailRepo.delete(g2e); }
        user2EmailRepo.delete(found);
        return 0;
    }

    @RequestMapping(path = "/config/g2e-add",method = RequestMethod.POST)
    @ResponseBody
    public int g2eAdd(String group, String email, HttpSession session) {
        if(!SessionAttribute.isSessionAvailable(session)) return 4;

        System.out.println(group);
        System.out.println(email);
        Optional<GroupUUID> found1 = groupUUIDRepo.findByGuid(group);
        if(found1.isEmpty()) return 1;

        Optional<User2Email> found2 = user2EmailRepo.findById(email);
        if(found2.isEmpty()) return 2;

        Group2EmailPK findKey = new Group2EmailPK();
        findKey.setEmail(email);
        findKey.setGuid(found1.get().getGuid());
        Optional<Group2Email> found3 = group2EmailRepo.findById(findKey);
        if(found3.isPresent()) return 3;

        Group2Email newG2E = new Group2Email();
        newG2E.setGuid(found1.get());
        newG2E.setEmail(found2.get());
        group2EmailRepo.saveAndFlush(newG2E);

        return 0;
    }

    @RequestMapping(path = "/config/g2e-delete",method = RequestMethod.POST)
    @ResponseBody
    public int g2eDelete(String group, String email, HttpSession session) {
        if(!SessionAttribute.isSessionAvailable(session)) return 4;

        System.out.println(group);
        System.out.println(email);
        Optional<GroupUUID> found1 = groupUUIDRepo.findByGuid(group);
        if(found1.isEmpty()) return 1;

        Optional<User2Email> found2 = user2EmailRepo.findById(email);
        if(found2.isEmpty()) return 2;

        Group2EmailPK findKey = new Group2EmailPK();
        findKey.setEmail(email);
        findKey.setGuid(found1.get().getGuid());
        Optional<Group2Email> found3 = group2EmailRepo.findById(findKey);
        if(found3.isPresent()) return 3;

        Group2Email newG2E = new Group2Email();
        newG2E.setGuid(found1.get());
        newG2E.setEmail(found2.get());
        group2EmailRepo.delete(newG2E);

        return 0;
    }

    @RequestMapping(path = "/config/group-update",method = RequestMethod.POST)
    @ResponseBody
    public int groupUpdate(HttpSession session) {
        if(!SessionAttribute.isSessionAvailable(session)) return 2;
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("date", new Date())
                    .toJobParameters();
            jobLauncher.run(updateGroupJob, jobParameters);
        } catch (Exception e) {
            log.info(e.getMessage());
            return 1;
        }
        return 0;
    }


    // ------------------------------------------------------------------ rekey ---------------------------------------------- //
    @GetMapping(path="/rekey")
    public String rekey(String groupName, String sobjectName, Model model) throws ApiException {
        //Verify client to access groups and keys
        String server = "https://sdkms.fortanix.com";
        String username = "ysoh1205@g.skku.edu";
        String password = "fortanixskku@";
        ApiClient client;

        client = generateFortanixSDKMSClientAndVerify(server, username, password);

        KeyObject sobject = getSecurityObjectByName(client, sobjectName);
        System.out.println(sobject.getState().getValue());

        KeyObject keyObject;
        if(sobject.getState().getValue().equals("Deactivated")){
            keyObject = rotateKey(client, sobject);
        }else{
            keyObject = sobject;
        }

        model.addAttribute("result", keyObject.toString());
        return "rekeyResult";
    }

    //for job testing.
    @GetMapping("/launchjob")
    public String handle() throws Exception {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("date", new Date())
                    .toJobParameters();
            jobLauncher.run(simpleJob, jobParameters);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("date", new Date())
                    .toJobParameters();
            jobLauncher.run(updateGroupJob, jobParameters);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "testPage";
    }
}
