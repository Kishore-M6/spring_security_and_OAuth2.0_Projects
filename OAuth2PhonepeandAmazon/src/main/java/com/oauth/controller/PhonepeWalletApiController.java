package com.oauth.controller;

import com.oauth.dto.MerchantPayment;
import com.oauth.dto.MerchantPaymentResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/phonepe"
        ,produces = MediaType.APPLICATION_JSON_VALUE
        ,consumes = MediaType.APPLICATION_JSON_VALUE)
public class PhonepeWalletApiController {
    @PostMapping("/payMerchant")
    public MerchantPaymentResponse payMerchant(@RequestBody MerchantPayment merchantPayment){

        return  MerchantPaymentResponse.of()
                .transactionNo(UUID.randomUUID().toString().substring(0,13))
                .transactionDate(LocalDate.now())
                .phonepeAccountNo(merchantPayment.getPhonepeAccountNo())
                .merchantAccountNo(merchantPayment.getMerchantAccountNo())
                .amount(merchantPayment.getAmount())
                .remarks(merchantPayment.getRemarks())
                .transactionStatus("Success").build();

    }
}
