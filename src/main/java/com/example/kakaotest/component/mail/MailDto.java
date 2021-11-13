package com.example.kakaotest.component.mail;

import lombok.Data;

@Data
public class MailDto {
    private String address;
    private String title;
    private String message;

    public MailDto(String address, String title, String message) {
        this.address = address;
        this.title = title;
        this.message = message;
    }
}
