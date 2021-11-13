package com.example.kakaotest.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "UserInfo")
public class UserDataModel {

    @Id
    private String id;

    @Column
    private byte[] pw;

    public UserDataModel() {
        super();
    }
    public UserDataModel(String id, byte[] pw)
    {
        this.id = id;
        this.pw = pw;
    }
}