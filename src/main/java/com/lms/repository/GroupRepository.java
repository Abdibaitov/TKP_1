package com.lms.repository;

import com.lms.model.entities.Group;
import com.lms.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    @Query("select s from User s where s.firstName=?1")
     List<User> getStudentByName(String name);
}
