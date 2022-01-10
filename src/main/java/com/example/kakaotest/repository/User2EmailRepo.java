package com.example.kakaotest.repository;

import com.example.kakaotest.model.User2Email;
import com.example.kakaotest.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface User2EmailRepo extends JpaRepository<User2Email, String> {
//    Optional<User2Email> findByEmail(String email);
    List<User2Email> findAllByUserCode(UserInfo userInfo);
}
