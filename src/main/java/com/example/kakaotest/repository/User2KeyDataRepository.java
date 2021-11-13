package com.example.kakaotest.repository;

import com.example.kakaotest.model.User2EmailModel;
import com.example.kakaotest.model.User2KeyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User2KeyDataRepository extends JpaRepository<User2KeyModel, Long>{
}
