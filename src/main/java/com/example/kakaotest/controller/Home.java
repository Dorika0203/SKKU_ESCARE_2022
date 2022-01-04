package com.example.kakaotest.controller;


import com.example.kakaotest.component.SessionAttribute;

import com.example.kakaotest.component.mail.ExternalEnv;
import com.example.kakaotest.component.mail.MailDto;
import com.example.kakaotest.component.mail.MailService;
import com.example.kakaotest.model.AdminModel;
import com.example.kakaotest.repository.AdminRepository;
import com.fortanix.sdkms.v1.ApiClient;
import com.fortanix.sdkms.v1.ApiException;
import com.fortanix.sdkms.v1.model.Group;
import com.fortanix.sdkms.v1.model.KeyObject;
import com.fortanix.sdkms.v1.model.Plugin;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

import static com.example.kakaotest.fortanix.FortanixRestApi.*;
import static org.apache.commons.codec.digest.DigestUtils.sha256;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
@Controller
@AllArgsConstructor
public class Home {


    AdminRepository adminRepository;
    private final JobLauncher jobLauncher;
    private final Job job;
    private final JavaMailSender javaMailSender;
    private final ExternalEnv env;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login()
    {
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

    //Display exportable keys with their groups, which have KCV.
    @GetMapping(path="/export")
    public String export(Model model) throws ApiException {
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

    @GetMapping(path="group")
    public String group(){

        return "group";
    }


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

//    @RequestMapping(path = "/signup", method = RequestMethod.GET)
//    public String signup()
//    {
//        return "signup";
//    }
//
//    @ResponseBody
//    @RequestMapping(path = "/signup", method = RequestMethod.POST)
//    public int signupRequest(String id, String pw) throws NoSuchAlgorithmException
//    {
//        log.info(id + " " + pw);
//        if(id.length()<1) return 1; // id short
//        if(pw.length()<1) return 2; // pw short
//        if(adminRepository.findById(id).isPresent()) return 3; // already exist
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        md.update(pw.getBytes());
//        AdminModel newUser = new AdminModel(id, md.digest());
//        adminRepository.saveAndFlush(newUser);
//        return 0; // success
//    }

    //for job testing.
    @GetMapping("/launchjob")
    public String handle() throws Exception {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("date", new Date())
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "testPage";
    }
}
