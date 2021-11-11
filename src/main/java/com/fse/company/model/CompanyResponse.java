package com.fse.company.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyResponse {
    private String companyCode;
    private String companyName;
    private String companyCeo;
    private long companyTurnover;
    private String companyWebsite;
    private double stockPrice;

    public CompanyResponse(Company company, double stockPrice) {
        this.companyCode=company.getCompanyCode();
        this.companyName=company.getCompanyName();
        this.companyCeo=company.getCompanyCeo();
        this.companyTurnover=company.getCompanyTurnover();
        this.companyWebsite=company.getCompanyWebsite();
        this.stockPrice=stockPrice;
    }
}
