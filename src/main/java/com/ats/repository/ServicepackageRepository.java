package com.ats.repository;

import com.ats.entity.Servicepackage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ServicepackageRepository extends JpaRepository<Servicepackage, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Servicepackage b SET b.status = :newStatus WHERE b.id = :id")
    int changeStatus(@Param("id") int id, @Param("newStatus") String newStatus);


    @Query("Select s from Servicepackage s " +
            "where s.status LIKE CONCAT('%',LOWER(:status),'%') and " +
            "s.name LIKE CONCAT('%',LOWER(:search),'%')")
    Page<Servicepackage> findAllBySearch(Pageable pageable,
                                 @Param("search") String search,
                                 @Param("status") String status);
}
