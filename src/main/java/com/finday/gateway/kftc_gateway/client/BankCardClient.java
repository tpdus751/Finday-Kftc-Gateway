package com.finday.gateway.kftc_gateway.client;

import com.finday.gateway.kftc_gateway.dto.AccountDTO;
import com.finday.gateway.kftc_gateway.dto.CardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BankCardClient {

    private final WebClient webClient;
    private final Environment env;

    public List<CardDTO> getCards(String bankKey, String userSpecificNo) {
        String baseUrl = env.getProperty("bank." + bankKey + ".url");

        return webClient.get()
                .uri(baseUrl + "/" + bankKey + "/cards?userSpecificNo={userSpecificNo}", userSpecificNo)
                .retrieve()
                .bodyToFlux(CardDTO.class)
                .collectList()
                .block(); // 동기 수집
    }

}
