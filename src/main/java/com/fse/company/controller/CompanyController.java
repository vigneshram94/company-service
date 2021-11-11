package com.fse.company.controller;

import com.fse.company.exception.CompanyAlreadyExistsException;
import com.fse.company.exception.CompanyTurnoverException;
import com.fse.company.exception.FieldsMissingException;
import com.fse.company.model.Company;
import com.fse.company.model.CompanyResponse;
import com.fse.company.service.CompanyServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1.0/market/company")
@SecurityRequirement(name = "companyapi")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> addCompany(@RequestBody Company company) throws CompanyAlreadyExistsException, CompanyTurnoverException, FieldsMissingException {
        companyService.addCompany(company);
        return new ResponseEntity<Object>(company, HttpStatus.CREATED);
    }

    @GetMapping("/info/{companyCode}")
    public CompanyResponse findByCompanyCode(@PathVariable String companyCode, HttpServletRequest request) {
        return companyService.findByCompanyCode(companyCode, request);
    }

    @GetMapping("/getAll")
    public List<CompanyResponse> getCompanies(HttpServletRequest request) {
        List<CompanyResponse> companies = companyService.getCompanies(request);
        return companies;
    }

    @GetMapping("/findAll")
    public List<String> findAllCompanies() {
        List<String> companies = companyService.findAllCompanies();
        return companies;
    }


    @DeleteMapping("/delete/{companyCode}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteCompany(@PathVariable String companyCode, HttpServletRequest request) {
        companyService.deleteCompany(companyCode, request);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
