package com.adacapstone.seattleinyourlens.repository;


import com.adacapstone.seattleinyourlens.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Boot automatically writes the SQL for you!
}