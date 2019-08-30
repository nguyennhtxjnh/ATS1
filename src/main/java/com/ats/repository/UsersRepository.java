package com.ats.repository;

import com.ats.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query("Select b from Users b where b.email = :email")
    Users findAccountByEmail(@Param("email") String email);

    @Query("Select b from Users b where b.accessToken = :accessToken")
    Users findAccountByToken(@Param("accessToken") String token);

    @Transactional
    @Modifying
    @Query("UPDATE Users b SET b.lastLogin = :lastDate WHERE b.email = :email and  b.accessToken = :accessToken")
    void editAccountLastLogin(@Param("lastDate") Date date, @Param("email") String email,
                              @Param("accessToken") String accessToken);

    @Transactional
    @Modifying
    @Query("UPDATE Users b SET b.password = :newPassword WHERE b.id = :id")
    int changePassword(@Param("id") int id, @Param("newPassword") String newPassword);

    @Transactional
    @Modifying
    @Query("UPDATE Users b SET b.password = :newPassword WHERE b.accessToken = :token")
    int forgotPassword(@Param("token") String token, @Param("newPassword") String newPassword);

    @Transactional
    @Modifying
    @Query("UPDATE Users b SET b.roleId = :role WHERE b.id = :id")
    int changeRole(@Param("id") int id, @Param("role") int role);

    @Transactional
    @Modifying
    @Query("UPDATE Users b SET b.status = :newStatus WHERE b.id = :id")
    int changeStatus(@Param("id") int id, @Param("newStatus") String newStatus);

    @Transactional
    @Modifying
    @Query("UPDATE Users b SET b.status = :newStatus WHERE b.accessToken = :token")
    int confirmUser(@Param("token") String token, @Param("newStatus") String newStatus);

    Optional<Users> findById(int id);

    @Query("select u from Users u where " +
            "u.status LIKE CONCAT('%',LOWER(:status),'%') and u.roleId <> 4 and" +
            "(u.email LIKE CONCAT('%',LOWER(:search),'%') or " +
            "u.fullName LIKE CONCAT('%',LOWER(:search),'%'))")
    Page<Users> findAll(Pageable pageable,
                        @Param("search") String search,
                        @Param("status") String status);
}
