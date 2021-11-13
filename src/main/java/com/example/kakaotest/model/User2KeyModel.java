package com.example.kakaotest.model;


import lombok.Data;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "User2Key")
public class User2KeyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @JoinColumn(name = "UserInfo_id")
    @ManyToOne
    private UserDataModel user;

    @Column
    private String UUID;
}
