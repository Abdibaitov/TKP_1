package com.lms.repository;

import com.lms.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<String, Long> {
    @Query(" select s from User s join Group g ON s.group.id = g.id join g.courses c join Company com ON c.company.id = com.id where com.id=:id")
    List<User> getStudentByCompany(@Param("id") Long companyId);

    @Query("select s from User s where s.firstName=?1")
    List<User> getStudentByName(String name);

    @Query("select s from User s join Group g ON s.group.id = g.id join g.courses c join User t ON c.teacher.id = t.id where t.id=:id")
    List<User> getStudentsByTeacher(@Param("id") Long teacherId);

}
