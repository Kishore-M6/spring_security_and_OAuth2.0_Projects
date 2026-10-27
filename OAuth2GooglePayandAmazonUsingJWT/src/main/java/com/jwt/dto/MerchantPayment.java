package com.jwt.dto;

import lombok.Data;

@Data
public class MerchantPayment {
    String googlePayAccountNo;
    String merchantAccountNo;
    Double amount;
    String remarks;
}
