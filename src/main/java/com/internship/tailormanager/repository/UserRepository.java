package com.internship.tailormanager.repository;

import com.internship.tailormanager.dto.UserGetDto;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User getUserByEmail(String email);

    User getUserByEmailAndStatus(String email,Status status);

    Page<User> findUserByStatus(Status status, Pageable pageable);

    Boolean existsUserByEmailAndStatus(String email,Status status);

    @Modifying
    @Query("update User u set u.password=:password where  u.email=:email")
    void updateUserByEmail(@Param("email") String email, @Param("password") String password);
}