package com.example.kakaotest.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "groupuuid")
public class GroupUUID {

    @Id
    private String group_name;

    @Column(unique = true)
    private String guid;
}
