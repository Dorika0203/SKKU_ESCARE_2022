package com.example.kakaotest.repository;

import com.example.kakaotest.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminModel, String>{
}
