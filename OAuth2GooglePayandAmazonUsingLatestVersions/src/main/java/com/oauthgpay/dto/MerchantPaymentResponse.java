package com.oauthgpay.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(builderMethodName = "of")
public class MerchantPaymentResponse {
    String transactionNo;
    LocalDate transactionDate;
    String googlePayAccountNo;
    String merchantAccountNo;
    Double amount;
    String remarks;
    String transactionStatus;
}
