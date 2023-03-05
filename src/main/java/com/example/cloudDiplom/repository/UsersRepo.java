package com.example.cloudDiplom.repository;

import com.example.cloudDiplom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsersRepo extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
