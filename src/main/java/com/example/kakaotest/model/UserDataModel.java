package com.example.kakaotest.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "UserInfo")
public class UserDataModel {
    @Id
    private String id;

    @Column
    private byte[] pw;

    public UserDataModel(String _id, byte[] _pw) {
        this.id = _id;
        this.pw = _pw;
    }

    public UserDataModel() {
        super();
    }
}