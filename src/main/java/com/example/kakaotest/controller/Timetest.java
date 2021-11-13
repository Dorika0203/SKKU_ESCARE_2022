package com.example.kakaotest.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;


// 시간 딜레이로 Job 실행 예시
// 여러가지 job 중 선택하는 방법

@Slf4j
@Configuration
@EnableScheduling
public class Timetest{

    private final JobLauncher jobLauncher;
    private final Job job;

    public Timetest(JobLauncher jobLauncher, @Qualifier("simpleJob") Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Scheduled(cron = "0 00 00 * * *") // 10초
    public void simpleJobIterable() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("date",  new Date())
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
