package com.ats.repository;

import com.ats.entity.Userslikecv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserlikecvRepository extends JpaRepository<Userslikecv, Integer> {
    @Query("Select u from Userslikecv u where u.userId = :userid and u.cvid = :cvid")
    List<Userslikecv> check(@Param("userid") int userid, @Param("cvid") int cvid);

    List<Userslikecv> findUserslikecvsByUserId(int EmployerId);
}