package com.oauthgpay.dto;

import lombok.Data;

@Data
public class MerchantPayment {
    String googlePayAccountNo;
    String merchantAccountNo;
    Double amount;
    String remarks;
}
