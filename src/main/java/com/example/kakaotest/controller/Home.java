package com.example.kakaotest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
public class Home {

    @RequestMapping("/")
    public String hello() {
        return "/home_page";
    }

    @GetMapping("/launchjob")
    public String handle() throws Exception {

//        try {
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addDate("date",  new Date())
//                    .toJobParameters();
//            jobLauncher.run(job, jobParameters);
//        } catch (Exception e) {
//            log.info(e.getMessage());
//        }

        return "/home_page";
    }
}
