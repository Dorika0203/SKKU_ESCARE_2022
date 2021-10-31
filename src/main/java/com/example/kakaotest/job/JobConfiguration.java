package com.example.kakaotest.job;

import com.example.kakaotest.job.chunkorientedtask.FortanixAuthTasklet;
import com.example.kakaotest.job.chunkorientedtask.Step2ItemProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sound.sampled.Line;
import javax.sql.DataSource;

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
                .start(fortanixUserAuthenticate())
                .next(checkExpireDateByUUID())
                .build();
    }

    @Bean
    public Step fortanixUserAuthenticate() {
        return stepBuilderFactory.get("fortanixUserAuthenticate")
                .tasklet(new FortanixAuthTasklet())
                .listener(promotionListener())
                .build();
    }

    @Bean
    public ExecutionContextPromotionListener promotionListener () {
        ExecutionContextPromotionListener executionContextPromotionListener = new ExecutionContextPromotionListener();
        // 데이터 공유를 위해 사용될 key값을 미리 빈에 등록해주어야 합니다.
        executionContextPromotionListener.setKeys(new String[]{"SPECIFIC_MEMBER"});

        return executionContextPromotionListener;
    }

    @Bean
    public Step checkExpireDateByUUID() {
        return stepBuilderFactory.get("jdbcCursorItemReaderStep")
                .<String, String>chunk(3)
                .reader(step2ItemReader())
                .processor(step2ItemProcessor())
                .writer(itemWriter())
                .listener(promotionListener())
                .build();
    }

    @Bean
    public JdbcCursorItemReader<String> step2ItemReader() {
        return new JdbcCursorItemReaderBuilder<String>()
                .fetchSize(1)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(String.class))
                .sql("SELECT id FROM UUID")
                .name("JdbcCursorItemReader")
                .build();
    }


    private ItemProcessor<String, String> step2ItemProcessor() {
        return new Step2ItemProcessor();
        }
    }


    private ItemWriter<Line> itemWriter() {
        return new LinesWriter();
    }


}
