package com.example.kakaotest.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
public class AdminModel {
    @Id
    private String id;
    @Column
    private byte[] pw;

    public AdminModel(String id, byte[] pw) {this.id = id; this.pw = pw;}
    public AdminModel() {super();}
}
