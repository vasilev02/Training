package com.example.linkedOut.repository;

import com.example.linkedOut.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    Optional<Company> findCompanyByName(String name);

    @Query(value = "SELECT c.name FROM Company AS c ORDER BY c.name ASC")
    List<String> getCompaniesFromDB();

    @Query(value = "SELECT c FROM Company AS c ORDER BY c.name ASC")
    List<Company> getAllCompanies();

    Company findCompanyById(String id);

}
