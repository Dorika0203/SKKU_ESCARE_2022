package com.example.kakaotest.model;


import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "user2email")
public class User2Email {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long no;

    @Id
    private String email;

    @JoinColumn(name = "UserInfo_userCode")
    @ManyToOne
    private UserInfo userCode;


    public User2Email() {
        super();
    }
}
