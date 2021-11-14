package com.example.kakaotest.controller;


import com.example.kakaotest.component.SessionAttribute;

import com.example.kakaotest.model.AdminModel;
import com.example.kakaotest.repository.AdminRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Controller;
import static org.apache.commons.codec.digest.DigestUtils.sha256;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Controller
@AllArgsConstructor
public class Home {

    AdminRepository adminRepository;
    private final JobLauncher jobLauncher;
    private final Job job;

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
