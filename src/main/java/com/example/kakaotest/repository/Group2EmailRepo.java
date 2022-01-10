package com.example.kakaotest.repository;

import com.example.kakaotest.model.Group2Email;
import com.example.kakaotest.model.Group2EmailPK;
import com.example.kakaotest.model.User2Email;
import com.example.kakaotest.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Group2EmailRepo extends JpaRepository<Group2Email, Group2EmailPK> {
    List<Group2Email> findAllByEmail(User2Email user2Email);
}
