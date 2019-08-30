package com.ats.repository;

import com.ats.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("Select c from Company c")
    Page<Company> findAllPaging(Pageable pageable);

    Company findCompanyByNameCompany(String nameCompany);

    Optional<Company> findCompanyById(Integer id);

    Company findByEmail(String email);

    Company findByNameCompany(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Company c SET c.status = :newStatus WHERE c.id = :id")
    int changeStatus(@Param("id") int id, @Param("newStatus") String newStatus);

    @Query("select c from Company c " +
            "INNER JOIN c.employercompaniesById e " +
            "INNER JOIN e.usersByUserId u " +
            "where c.status LIKE CONCAT('%',LOWER(:status),'%') and " +
            "(c.nameCompany LIKE CONCAT('%',LOWER(:search),'%') or " +
            "u.email LIKE CONCAT('%',LOWER(:search),'%'))")
    Page<Company> findAll(Pageable pageable, @Param("search") String search, @Param("status") String status);

    @Query("select distinct c from Company c " +
            "INNER JOIN c.jobsById j " +
            "where j.status = 'approved' order by c.createdDate asc")
    List<Company> getFiveCompany();
}
