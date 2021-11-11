package com.fse.company.service;

import com.fse.company.exception.CompanyAlreadyExistsException;
import com.fse.company.exception.CompanyTurnoverException;
import com.fse.company.exception.FieldsMissingException;
import com.fse.company.model.Company;
import com.fse.company.model.CompanyResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CompanyService {
    public Company addCompany(Company company) throws CompanyAlreadyExistsException, CompanyTurnoverException, FieldsMissingException;

    public List<CompanyResponse> getCompanies(HttpServletRequest request);

    public void deleteCompany(String companyCode, HttpServletRequest request);

    public CompanyResponse findByCompanyCode(String companyCode, HttpServletRequest request);
}
