package com.example.kakaotest.job;

import com.example.kakaotest.component.mail.MailDto;
import com.example.kakaotest.component.mail.MailService;
import com.example.kakaotest.job.chunkorientedtask.FortanixAuthTasklet;
import com.example.kakaotest.job.chunkorientedtask.UUIDItemProcessor;
import com.example.kakaotest.job.chunkorientedtask.UUIDItemWriter;
import com.example.kakaotest.model.Group2Email;
import com.example.kakaotest.model.GroupUUIDModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Slf4j // log 사용을 위한 lombok 어노테이션
@RequiredArgsConstructor // 생성자 DI를 위한 lombok 어노테이션
@Configuration
@EnableBatchProcessing
@Service
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory; // 생성자 DI 받음
    private final StepBuilderFactory stepBuilderFactory; // 생성자 DI 받음
    private final DataSource dataSource;
    private final JavaMailSender javaMailSender;


    // JOBS
    @Bean
    public Job simpleJob() {
        log.info("------------------------------------------------ Job Started! ------------------------------------------");
        return jobBuilderFactory.get("simpleJob")
                .start(checkExpireDateByUUID(null))
                .build();
    }

//    @Bean
//    public Job updateGroups() {
//
//    }



    // STEPS
    @Bean
    @JobScope
    public Step fortanixUserAuthenticate(@Value("#{jobParameters[date]}") Date requestDate) {
        log.info(">>>>> requestDate = {}", requestDate);
        return stepBuilderFactory.get("fortanixUserAuthenticate")
                .tasklet(new FortanixAuthTasklet())
                .build();
    }


    @Bean
    @JobScope
    public Step checkExpireDateByUUID(@Value("#{jobParameters[date]}") Date requestDate) {
        log.info("check expire date by GUID --- >>>>> requestDate = {}", requestDate);
        return stepBuilderFactory.get("jdbcCursorItemReaderStep")
                .<Group2Email, List<MailDto>>chunk(1)
                .reader(UUIDItemReader())
                .processor(UUIDItemProcessor())
                .writer(UUIDItemWriter())
                .build();
    }
    private JdbcCursorItemReader<Group2Email> UUIDItemReader() {
        return new JdbcCursorItemReaderBuilder<Group2Email>()
                .fetchSize(2)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Group2Email.class))
                .sql("SELECT * from group2email")
                .name("JdbcCursorItemReader")
                .build();
    }
    private ItemProcessor<Group2Email, List<MailDto>> UUIDItemProcessor() {
        return new UUIDItemProcessor();
    }
    private ItemWriter<List<MailDto>> UUIDItemWriter() {
        MailService mailService = new MailService(javaMailSender);
        return new UUIDItemWriter(mailService);
    }



}
