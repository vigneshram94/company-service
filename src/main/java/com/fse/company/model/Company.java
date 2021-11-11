package com.fse.company.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "company_code")
    private String companyCode;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "company_ceo")
    private String companyCeo;
    @Column(name = "company_turnover")
    private long companyTurnover;
    @Column(name = "company_website")
    private String companyWebsite;
    @Column(name = "stock_exchange")
    private String stockExchange;
}
