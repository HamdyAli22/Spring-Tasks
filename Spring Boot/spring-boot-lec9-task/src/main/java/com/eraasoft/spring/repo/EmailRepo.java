package com.eraasoft.spring.repo;

import com.eraasoft.spring.dto.EmailDto;
import com.eraasoft.spring.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepo extends JpaRepository<Email,Long> {
    boolean existsByContent(String content);
    List<Email> findByNameIgnoreCase(String name);
    List<Email> findByNameInIgnoreCase(List<String> names);
    List<Email> findByContentIgnoreCase(String content);
}
