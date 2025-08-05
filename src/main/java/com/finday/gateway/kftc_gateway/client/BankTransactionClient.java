package com.finday.gateway.kftc_gateway.client;

import com.finday.gateway.kftc_gateway.dto.CardDTO;
import com.finday.gateway.kftc_gateway.dto.ResponsedTransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BankTransactionClient {

    private final WebClient webClient;
    private final Environment env;

    public List<ResponsedTransactionDTO> getLatest30DayTransaction(String bankKey, String userSpecificNo) {
        String baseUrl = env.getProperty("bank." + bankKey + ".url");

        return webClient.get()
                .uri(baseUrl + "/" + bankKey + "/transaction?userSpecificNo={userSpecificNo}&period=30Days", userSpecificNo)
                .retrieve()
                .bodyToFlux(ResponsedTransactionDTO.class)
                .collectList()
                .block(); // 동기 수집
    }

    public List<ResponsedTransactionDTO> getMonthTransaction(String bankKey, String userSpecificNo, String month) {
        String baseUrl = env.getProperty("bank." + bankKey + ".url");

        return webClient.get()
                .uri(baseUrl + "/" + bankKey + "/transaction?userSpecificNo={userSpecificNo}&period={month}", userSpecificNo, month)
                .retrieve()
                .bodyToFlux(ResponsedTransactionDTO.class)
                .collectList()
                .block(); // 동기 수집
    }

}
