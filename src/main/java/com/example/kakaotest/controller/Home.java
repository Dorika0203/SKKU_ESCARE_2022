package com.example.kakaotest.controller;


import com.example.kakaotest.component.SessionAttribute;

import com.example.kakaotest.model.UserDataModel;
import com.example.kakaotest.repository.UserDataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Controller
@AllArgsConstructor
public class Home {

    UserDataRepository userDataRepository;

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
        Optional<UserDataModel> op = userDataRepository.findById(id);
        if(op.isEmpty()) return 3; // no such id exist
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pw.getBytes());
        if(!Arrays.equals(op.get().getPw(), md.digest())) return 4; // pw wrong

        SessionAttribute.setSessionUserID(id, session);
        session.setMaxInactiveInterval(3600); // 1hour
        return 0;
    }

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public String signup()
    {
        return "signup";
    }

    @ResponseBody
    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public int signupRequest(String id, String pw) throws NoSuchAlgorithmException
    {
        log.info(id + " " + pw);
        if(id.length()<1) return 1; // id short
        if(pw.length()<1) return 2; // pw short
        if(userDataRepository.findById(id).isPresent()) return 3; // already exist
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pw.getBytes());
        UserDataModel newUser = new UserDataModel(id, md.digest());
        userDataRepository.saveAndFlush(newUser);
        return 0; // success
    }
}
