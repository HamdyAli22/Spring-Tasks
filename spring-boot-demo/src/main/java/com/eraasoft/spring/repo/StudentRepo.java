package com.eraasoft.spring.repo;

import com.eraasoft.spring.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
 //   Student findByUserName(String userName);
 //@Query(value = "select * from school_student where user_name = :username",nativeQuery = true)
 @Query(value = "select student from school_student student where student.userName = :username")
 List<Student> extractByUserName(@Param("username") String userName);
}
