package com.example.kakaotest.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "User2Email")
public class User2EmailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @JoinColumn(name = "UserInfo_id")
    @ManyToOne
    private UserDataModel user;

    @Column
    private String email;
}
