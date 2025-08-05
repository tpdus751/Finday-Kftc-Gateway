package com.finday.gateway.kftc_gateway.service;

import com.finday.gateway.kftc_gateway.client.BankAccountClient;
import com.finday.gateway.kftc_gateway.client.BankCardClient;
import com.finday.gateway.kftc_gateway.dto.AccountDTO;
import com.finday.gateway.kftc_gateway.dto.CardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardAggregationService {

    private final BankCardClient bankCardClient;

    public List<CardDTO> fetchSelectedBankCards(String userSpecificNo, String bankName) {
        if (bankName == null) {
            throw new IllegalArgumentException("bankName 은행명이 존재하지 않습니다." + bankName);
        }

        try {
            return bankCardClient.getCards(bankName, userSpecificNo);
        } catch (Exception e) {
            // 로깅 또는 예외 wrapping 가능
            return Collections.emptyList(); // 실패 시 빈 리스트 반환
        }
    }
}
