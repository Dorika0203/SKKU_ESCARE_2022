package com.example.kakaotest.job;

import com.example.kakaotest.component.mail.MailDto;
import com.example.kakaotest.component.mail.MailService;
import com.example.kakaotest.job.chunkorientedtask.GetGroupListTasklet;
import com.example.kakaotest.job.chunkorientedtask.UUIDItemProcessor;
import com.example.kakaotest.job.chunkorientedtask.UUIDItemWriter;
import com.example.kakaotest.model.Group2Email;
import com.example.kakaotest.repository.Group2EmailRepo;
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
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collections;
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
    @Autowired
    private Group2EmailRepo group2EmailRepo;


    // JOBS
    @Bean
    public Job simpleJob() {
        log.info("------------------------------------------------ simple Job Started! ------------------------------------------");
        return jobBuilderFactory.get("simpleJob")
                .start(checkExpireDateByUUID(null))
                .build();
    }

    @Bean
    public Job updateGroupJob() {
        log.info("------------------------------------------------ updateGroup Job Started! ------------------------------------------");
        return jobBuilderFactory.get("updateGroupJob")
                .start(getGroupListStep(null))
                .build();
    }


    // STEPS
    @Bean
    @JobScope
    public Step checkExpireDateByUUID(@Value("#{jobParameters[date]}") Date requestDate) {
        log.info("Step : [Check Expire Date by GUID], requestDate = {}", requestDate);
        return stepBuilderFactory.get("jdbcCursorItemReaderStep")
                .<Group2Email, List<MailDto>>chunk(1)
                .reader(UUIDItemReader())
                .processor(UUIDItemProcessor())
                .writer(UUIDItemWriter())
                .build();
    }

    @Bean
    @JobScope
    public Step getGroupListStep(@Value("#{jobParameters[date]}") Date requestDate) {
        log.info("Step: [Get Group List]");
        return stepBuilderFactory.get("getGroupListStep")
                .tasklet(getGroupListTasklet())
                .build();
    }

    // METHODS FOR GETTING STEP ELEMENT(READER, WRITER, PROCESSOR, TASKLET) INSTANCES
    @Bean
    public RepositoryItemReader<Group2Email> UUIDItemReader() {
//        return new JdbcCursorItemReaderBuilder<Group2Email>()
//                .fetchSize(1)
//                .dataSource(dataSource)
//                .rowMapper(new BeanPropertyRowMapper<>(Group2Email.class))
//                .sql("SELECT * from group2email")
//                .name("JdbcCursorItemReader")
//                .build();
        return new RepositoryItemReaderBuilder<Group2Email>()
                .repository(group2EmailRepo)
                .methodName("findAll")
                .pageSize(1)
                .maxItemCount(1)
                .sorts(Collections.singletonMap("guid", Sort.Direction.ASC))
                .name("repositoryItemReader")
                .build();
    }
    @Bean
    public ItemProcessor<Group2Email, List<MailDto>> UUIDItemProcessor() {
        return new UUIDItemProcessor();
    }
    @Bean
    public ItemWriter<List<MailDto>> UUIDItemWriter() {
        MailService mailService = new MailService(javaMailSender);
        return new UUIDItemWriter(mailService);
    }
    @Bean
    public GetGroupListTasklet getGroupListTasklet() {
        return new GetGroupListTasklet();
    }
}
