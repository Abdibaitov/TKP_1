package com.lms.repository;

import com.lms.model.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository {
    @Query("select  u from User u where upper(u.firstName) like  concat('%',:text,'%')" +
            "or  upper(u.lastName) like  concat('%',:text,'%')  ")
    List<User> searchAndPagination(@Param("text")String  text, Pageable pageable);
    @Query("select u from User u where u.firstName=:username")
    User getUserByName(@Param("username") String username);
    Optional<User> findByEmail(String email);
}
