package com.finday.gateway.kftc_gateway.service;

import com.finday.gateway.kftc_gateway.client.BankTransferClient;
import com.finday.gateway.kftc_gateway.dto.TransferRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final BankTransferClient bankTransferClient;

    @Transactional
    public void transfer(TransferRequestDTO request) {
        // 1. 출금
        try {
            bankTransferClient.withdraw(request.getFromBankName(), request)
                    .block();  // 동기 처리
        } catch (Exception e) {
            throw new RuntimeException("출금 실패: " + e.getMessage());
        }

        // 2. 입금
        try {
            bankTransferClient.deposit(request.getToBankName(), request)
                    .block();
        } catch (Exception e) {
            // 입금 실패 시 출금 롤백 시도
            rollbackWithdrawal(request);

            throw new RuntimeException("입금 실패: " + e.getMessage());
        }
    }

    private void rollbackWithdrawal(TransferRequestDTO request) {
        try {
            bankTransferClient.cancelWithdraw(request.getFromBankName(), request).block();
        } catch (Exception e) {
            System.err.println("출금 롤백 실패: " + e.getMessage());
        }
    }
}
