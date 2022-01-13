package com.example.kakaotest.repository;

import com.example.kakaotest.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface Group2EmailRepo extends JpaRepository<Group2Email, Group2EmailPK> {
    List<Group2Email> findAllByEmail(User2Email user2Email);
    Optional<Group2Email> findByEmailAndGuid(User2Email email, GroupUUID guid);
}
