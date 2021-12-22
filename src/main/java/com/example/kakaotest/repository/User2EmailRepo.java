package com.example.kakaotest.repository;

import com.example.kakaotest.model.User2Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User2EmailRepo extends JpaRepository<User2Email, String> {
}
