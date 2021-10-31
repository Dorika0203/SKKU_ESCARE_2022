package com.example.kakaotest.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "GroupsCheckedTime")
public class GroupsCheckedTimeModel {

    @Id
    private String uuid;

    @Column
    private String time;
}
