package com.eraasoft.spring.repo;

import com.eraasoft.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    boolean existsByName(String name);
}
