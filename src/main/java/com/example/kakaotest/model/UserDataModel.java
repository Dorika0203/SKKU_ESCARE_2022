package com.example.kakaotest.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "UserInfo")
public class UserDataModel {


    // 유저 아이디 (이름 예정)
    @Id
    private String id;


    // 유저 코드번호 -> 다른 DB에서 유저 관련 정보 찾을 때 이용 예정.
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userCode;

    public UserDataModel() {
        super();
    }
    public UserDataModel(String id, long userCode)
    {
        this.id = id;
        this.userCode = userCode;
    }
}