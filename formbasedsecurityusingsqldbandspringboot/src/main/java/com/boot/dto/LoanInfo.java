package com.boot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "of")
public class LoanInfo {
    private String loanNo;
    private String loanHolderName;
    private String loanType;
    private int tenure;
    private double emiAmount;

}
