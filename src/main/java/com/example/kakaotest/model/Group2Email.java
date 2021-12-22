package com.example.kakaotest.model;


import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "group2email")
@IdClass(Group2EmailPK.class)
public class Group2Email {

    @Id
    @JoinColumn(name = "User2Email_email")
    @ManyToOne
    private User2Email email;

    @Id
    @JoinColumn(name = "GroupUUID_guid")
    @ManyToOne
    private GroupUUID guid;
}
