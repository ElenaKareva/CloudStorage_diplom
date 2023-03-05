package com.example.cloudDiplom.repository;

import com.example.cloudDiplom.entity.FileEnt;
import com.example.cloudDiplom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface FileRepo extends JpaRepository<FileEnt, Long> {

    Optional<FileEnt> findByFileName(String fileName);

    void deleteByFileName(String fileName);

    @Modifying
    @Query("UPDATE files f SET f.fileName = :newName WHERE f.fileName = :fileName")
    Optional<FileEnt> updateFileName(@Param(value = "fileName") String fileName, @Param(value = "newName") String newName);

    List<FileEnt> findAllByUser(User user);
}
