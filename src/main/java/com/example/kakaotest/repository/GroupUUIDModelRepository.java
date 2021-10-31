package com.example.kakaotest.repository;

import com.example.kakaotest.model.GroupUUIDModel;
import com.example.kakaotest.model.UserDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupUUIDModelRepository extends JpaRepository<GroupUUIDModel, Long> {
}
