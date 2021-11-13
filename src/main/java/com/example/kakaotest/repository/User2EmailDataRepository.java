package com.example.kakaotest.repository;

import com.example.kakaotest.model.User2EmailModel;
import com.example.kakaotest.model.UserDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User2EmailDataRepository extends JpaRepository<User2EmailModel, Long>{
}
