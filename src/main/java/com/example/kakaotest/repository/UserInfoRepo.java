package com.example.kakaotest.repository;

import com.example.kakaotest.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
}
