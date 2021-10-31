package com.example.kakaotest.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "UUID")
public class GroupUUIDModel {

    @Id
    private long id;

    @Column
    private String uuid;
}
