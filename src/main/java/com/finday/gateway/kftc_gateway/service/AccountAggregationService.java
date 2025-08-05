package com.finday.gateway.kftc_gateway.service;

import com.finday.gateway.kftc_gateway.client.BankAccountClient;
import com.finday.gateway.kftc_gateway.dto.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountAggregationService {

    private final BankAccountClient bankAccountClient;

    public List<AccountDTO> fetchAllAccounts(String userSpecificNo) {
        // 병렬로 각 은행에 요청 (Future, ParallelStream 등 활용 가능)
        List<String> banks = List.of("kookmin", "shinhan", "hana", "woori", "nh", "sc", "kakao", "k", "toss");

        return banks.parallelStream()
                .map(bank -> {
                    try {
                        return bankAccountClient.getAccounts(bank, userSpecificNo);
                    } catch (Exception e) {
                        return Collections.<AccountDTO>emptyList(); // 실패 시 빈 리스트 처리
                    }
                })
                .flatMap(List::stream)
                .toList();
    }

    public List<AccountDTO> fetchSelectedBankAccounts(String userSpecificNo, String bankName) {
        if (bankName == null) {
            throw new IllegalArgumentException("bankName 은행명이 존재하지 않습니다." + bankName);
        }

        try {
            return bankAccountClient.getAccounts(bankName, userSpecificNo);
        } catch (Exception e) {
            // 로깅 또는 예외 wrapping 가능
            return Collections.emptyList(); // 실패 시 빈 리스트 반환
        }
    }

}
