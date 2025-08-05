package com.finday.gateway.kftc_gateway.service;

import com.finday.gateway.kftc_gateway.client.BankAccountClient;
import com.finday.gateway.kftc_gateway.client.BankTransactionClient;
import com.finday.gateway.kftc_gateway.dto.CardDTO;
import com.finday.gateway.kftc_gateway.dto.ResponsedTransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionAggregationService {

    private final BankTransactionClient bankTransactionClient;

    public List<ResponsedTransactionDTO> fetchLatest30DayTransaction(String bankName, String userSpecificNo) {
        if (bankName == null) {
            throw new IllegalArgumentException("bankName 은행명이 존재하지 않습니다." + bankName);
        }
        try {
            return bankTransactionClient.getLatest30DayTransaction(bankName, userSpecificNo);
        } catch (Exception e) {
            // 로깅 또는 예외 wrapping 가능
            return Collections.emptyList(); // 실패 시 빈 리스트 반환
        }
    }

    public List<ResponsedTransactionDTO> fetchMonthTransaction(String bankName, String userSpecificNo, String month) {
        if (bankName == null) {
            throw new IllegalArgumentException("bankName 은행명이 존재하지 않습니다." + bankName);
        }
        try {
            return bankTransactionClient.getMonthTransaction(bankName, userSpecificNo, month);
        } catch (Exception e) {
            // 로깅 또는 예외 wrapping 가능
            return Collections.emptyList(); // 실패 시 빈 리스트 반환
        }
    }



}
