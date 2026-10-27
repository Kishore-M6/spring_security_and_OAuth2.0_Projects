package com.oauth.dto;

import lombok.Data;

@Data
public class MerchantPayment {
    String phonepeAccountNo;
    String merchantAccountNo;
    Double amount;
    String remarks;
}
