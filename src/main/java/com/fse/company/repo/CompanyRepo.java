package com.fse.company.repo;

import com.fse.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {
    Company findByCompanyCode(String companyCode);

    @Query(value = "select distinct company_name from company",nativeQuery = true)
    List<String> findAllCompanies();
}