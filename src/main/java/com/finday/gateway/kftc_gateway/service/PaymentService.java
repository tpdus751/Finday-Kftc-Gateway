package com.finday.gateway.kftc_gateway.service;

import com.finday.gateway.kftc_gateway.client.BankPaymentClient;
import com.finday.gateway.kftc_gateway.client.BankTransferClient;
import com.finday.gateway.kftc_gateway.dto.requestedPaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final BankPaymentClient bankPaymentClient;

    public void pay(requestedPaymentDTO response) {
        try {
            bankPaymentClient.requestPayToBank(response).subscribe();
        } catch (Exception e) {
            throw new RuntimeException("결제 실패: " + e.getMessage());
        }
    }
}
