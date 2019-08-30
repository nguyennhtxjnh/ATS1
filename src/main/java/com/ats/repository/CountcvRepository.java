package com.ats.repository;

import com.ats.entity.Countcv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountcvRepository extends JpaRepository<Countcv, Integer> {
    @Query("Select c from Countcv c where c.cvid = :cvid and c.userId = :userid")
    Countcv findCountcv (@Param("cvid") int cvid, @Param("userid") int userid);

    int countCountcvsByCvid(int id);
}
