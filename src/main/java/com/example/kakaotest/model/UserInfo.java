package com.example.kakaotest.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "userinfo")
public class UserInfo {

    // 유저 코드번호 -> 다른 DB에서 유저 관련 정보 찾을 때 이용 예정.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userCode;

    // 유저 아이디 (이름 예정)
    @Column
    private String userName;
}