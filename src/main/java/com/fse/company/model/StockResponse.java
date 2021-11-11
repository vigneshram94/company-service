package com.fse.company.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StockResponse {
    private String companyCode;
    private double stockPrice;
    private LocalDateTime stockDate;
}
