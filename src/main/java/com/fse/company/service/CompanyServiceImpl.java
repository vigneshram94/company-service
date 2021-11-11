package com.fse.company.service;

import com.fse.company.exception.CompanyAlreadyExistsException;
import com.fse.company.exception.CompanyTurnoverException;
import com.fse.company.model.Company;
import com.fse.company.model.CompanyResponse;
import com.fse.company.model.StockResponse;
import com.fse.company.repo.CompanyRepo;
import com.fse.company.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.support.NullValue;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private JwtUtil jwtUtil;

    @Value("${stock.service.host}")
    private String stockHost;

    RestTemplate restTemplate = new RestTemplate();
    private final String getlatestStock = "/api/v1.0/market/stock/get/";
    private final String deleteCompanyStock = "/api/v1.0/market/stock/delete/";

    public Company addCompany(Company company) throws CompanyAlreadyExistsException, CompanyTurnoverException {
        Company exists = companyRepo.findByCompanyCode(company.getCompanyCode());
        if (exists != null ) {
            throw new CompanyAlreadyExistsException();
        }

        if (company.getCompanyTurnover() < 100000000) {
            throw new CompanyTurnoverException();
        }

        LOGGER.info("New company added with details : {}", company.toString());
        return companyRepo.save(company);
    }

    public List<CompanyResponse> getCompanies(HttpServletRequest request) {
        LOGGER.info("Returning list of companies");

        List<Company> companyList = companyRepo.findAll();
        List<CompanyResponse> companyResponseList = new ArrayList<>();
        for (Company comp : companyList) {
            ResponseEntity<StockResponse> stockResponse = getStockPrice(comp.getCompanyCode(),request);
            CompanyResponse response;
            if (stockResponse!=null && stockResponse.getBody() != null ) {
                response = new CompanyResponse(comp, stockResponse.getBody().getStockPrice());
            } else {
                response = new CompanyResponse(comp, 0);
            }
            companyResponseList.add(response);
        }
        return companyResponseList;
    }

    public void deleteCompany(String companyCode, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        String authToken = jwtUtil.extractJWT(request);
        headers.add("Authorization", authToken);
        LOGGER.info("Deleting company : {}", companyCode);
        Company temp = companyRepo.findByCompanyCode(companyCode);
        String url = stockHost.concat(deleteCompanyStock);
        restTemplate.exchange(
                url + companyCode,
                HttpMethod.DELETE,
                new HttpEntity<>("parameters", headers),
                NullValue.class);
        companyRepo.delete(temp);
    }

    public CompanyResponse findByCompanyCode(String companyCode, HttpServletRequest request) {
        LOGGER.info("Fetching details of company : {}", companyCode);
        /*String url = stockHost.concat(getlatestStock);
        HttpHeaders headers = new HttpHeaders();
        String authToken = jwtUtil.extractJWT(request);
        headers.add("Authorization", authToken);
        ResponseEntity<StockResponse> stockResponse = restTemplate.exchange(
                url + companyCode,
                HttpMethod.GET,
                new HttpEntity<>("parameters", headers),
                StockResponse.class);
        Company company = companyRepo.findByCompanyCode(companyCode);*/
        ResponseEntity<StockResponse> stockResponse = getStockPrice(companyCode,request);
        Company company = companyRepo.findByCompanyCode(companyCode);
        CompanyResponse response;
        if (stockResponse!= null && stockResponse.getBody() != null ) {
            response = new CompanyResponse(company, stockResponse.getBody().getStockPrice());
        } else {
            response = new CompanyResponse(company, 0);
        }
        return response;
    }

    public List<String> findAllCompanies() {
        return companyRepo.findAllCompanies();
    }

    public ResponseEntity<StockResponse> getStockPrice(String companyCode, HttpServletRequest request) {
        String url = stockHost.concat(getlatestStock);
        HttpHeaders headers = new HttpHeaders();
        String authToken = jwtUtil.extractJWT(request);
        headers.add("Authorization", authToken);
        ResponseEntity<StockResponse> stockResponse = null;
        try {
            stockResponse = restTemplate.exchange(
                    url + companyCode,
                    HttpMethod.GET,
                    new HttpEntity<>("parameters", headers),
                    StockResponse.class);
        } catch (Exception ex) {
            LOGGER.info(ex.toString());
        }
        return stockResponse;
    }
}
