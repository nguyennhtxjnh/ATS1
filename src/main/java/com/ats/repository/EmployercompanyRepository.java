package com.ats.repository;

import com.ats.entity.Employercompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployercompanyRepository extends JpaRepository<Employercompany,Integer> {

    @Query("Select b from Employercompany b where b.userId = :userId")
    Employercompany findCompanyByUserId(@Param("userId") int userId);

    @Query("Select b from Employercompany b where b.companyId = :companyId")
    List<Employercompany> findUserByCompanyId(@Param("companyId") int companyId);

    @Transactional
    @Modifying
    @Query("UPDATE Employercompany b SET b.status = :newStatus WHERE b.userId = :id")
    int changeStatus(@Param("id") int id, @Param("newStatus") String newStatus);

    @Query("Select e from Employercompany e " +
            "INNER JOIN e.usersByUserId u " +
            "where e.status LIKE CONCAT('%',LOWER(:status),'%') and " +
            "e.companyId = :companyId and "+
            "(u.fullName LIKE CONCAT('%',LOWER(:search),'%') OR " +
            "u.email LIKE CONCAT('%',LOWER(:search),'%'))")
    Page<Employercompany> getAll(Pageable pageable,
                                 @Param("search") String search,
                                 @Param("status") String status,
                                 @Param("companyId") int companyId);

    Employercompany findByUserId(int userId);
}
