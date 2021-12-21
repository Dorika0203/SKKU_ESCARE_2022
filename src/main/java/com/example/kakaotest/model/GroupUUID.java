package com.example.kakaotest.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "groupuuid")
public class GroupUUID {
    @Id
    private String guid;
    
    public GroupUUID() {super();}
}
