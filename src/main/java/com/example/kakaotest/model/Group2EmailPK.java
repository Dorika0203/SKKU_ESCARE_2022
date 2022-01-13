package com.example.kakaotest.model;

import lombok.Data;

import javax.persistence.JoinColumn;
import java.io.Serializable;

@Data
public class Group2EmailPK implements Serializable {
    private String email;
    private String guid;
}
