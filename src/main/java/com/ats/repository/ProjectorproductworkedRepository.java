package com.ats.repository;

import com.ats.entity.Projectorproductworked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectorproductworkedRepository extends JpaRepository<Projectorproductworked,Integer> {
    @Query("Select p from Projectorproductworked p where p.cvid = :id")
    List<Projectorproductworked> findByCVID(@Param("id") int id);
}
