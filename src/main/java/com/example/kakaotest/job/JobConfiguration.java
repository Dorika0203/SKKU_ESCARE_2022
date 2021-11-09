package com.example.kakaotest.job;

import com.example.kakaotest.component.UUID;
import com.example.kakaotest.job.chunkorientedtask.FortanixAuthTasklet;
import com.example.kakaotest.job.chunkorientedtask.Step2ItemProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j // log 사용을 위한 lombok 어노테이션
@RequiredArgsConstructor // 생성자 DI를 위한 lombok 어노테이션
@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory; // 생성자 DI 받음

    private final StepBuilderFactory stepBuilderFactory; // 생성자 DI 받음

    private final DataSource dataSource;

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
                .start(fortanixUserAuthenticate(null))
                .next(checkExpireDateByUUID(null))
                .build();
    }


    // 여러개 job 중 취사선택 예시 작성 위해서 생성
    @Bean
    public Job job2() {
        return jobBuilderFactory.get("job2").start(fortanixUserAuthenticate(null)).next(checkExpireDateByUUID(null)).build();
    }

    @Bean
    @JobScope
    public Step fortanixUserAuthenticate(@Value("#{jobParameters[date]}") Date requestDate) {
        log.info("------------------------------------------------ Job Started! ------------------------------------------");
        log.info(">>>>> requestDate = {}", requestDate);
        return stepBuilderFactory.get("fortanixUserAuthenticate")
                .tasklet(new FortanixAuthTasklet())
                .build();
    }

    @Bean
    @JobScope
    public Step checkExpireDateByUUID(@Value("#{jobParameters[date]}") Date requestDate) {
        return stepBuilderFactory.get("jdbcCursorItemReaderStep")
                .<UUID, List<HashMap>>chunk(1)
                .reader(step2ItemReader())
                .processor(step2ItemProcessor())
                .writer(step2ItemWriter())
                .build();
    }

    @Bean
    public JdbcCursorItemReader<UUID> step2ItemReader() {
        return new JdbcCursorItemReaderBuilder<UUID>()
                .fetchSize(1)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(UUID.class))
                .sql("SELECT * FROM uuid")
                .name("JdbcCursorItemReader")
                .build();
    }


    private ItemProcessor<UUID, List<HashMap>> step2ItemProcessor() {
        return new Step2ItemProcessor();
        }



    private ItemWriter<List<HashMap>> step2ItemWriter() {
        return items -> {
            for (List item : items) {
                for (int i = 0; i < item.size(); i++) {
                    HashMap<String, String> mailObject = (HashMap<String, String>) item.get(i);
                    log.info(mailObject.get("name"));
                }
            }
        };
    }


}
