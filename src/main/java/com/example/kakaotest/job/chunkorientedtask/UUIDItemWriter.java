package com.example.kakaotest.job.chunkorientedtask;

import com.example.kakaotest.component.mail.MailDto;
import com.example.kakaotest.component.mail.MailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class UUIDItemWriter implements ItemWriter<List<MailDto>> {

    private MailService mailService;

    @Override
    public void write(List<? extends List<MailDto>> items) throws Exception {
        items.get(0).forEach((mailObject) -> {
            System.out.println(mailObject.getAddress());
            mailService.mailSend(mailObject);
        });
    }

    @Autowired
    public UUIDItemWriter(MailService mailService) {
        this.mailService = mailService;
    }
}
