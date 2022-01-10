package com.example.kakaotest.repository;

import com.example.kakaotest.model.GroupUUID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupUUIDRepo extends JpaRepository<GroupUUID, Long> {
    Optional<GroupUUID> findByGuid(String guid);
    List<GroupUUID> findAllByOrderByGuid();
}