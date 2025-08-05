package com.finday.gateway.kftc_gateway.client;

import com.finday.gateway.kftc_gateway.dto.TransferRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BankTransferClient {

    private final WebClient webClient;
    private final Environment env;

    public Mono<Void> withdraw(String bankKey, TransferRequestDTO dto) {
        String url = env.getProperty("bank." + bankKey + ".url") + "/" + bankKey + "/withdraw";
        return webClient.post()
                .uri(url)
                .bodyValue(dto)
                .retrieve()
                .onStatus(status -> status.isError(), response ->
                        response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new RuntimeException("출금 실패: " + body)))
                )
                .bodyToMono(Void.class);
    }

    public Mono<Void> deposit(String bankKey, TransferRequestDTO dto) {
        String url = env.getProperty("bank." + bankKey + ".url") + "/" + bankKey + "/deposit";
        return webClient.post()
                .uri(url)
                .bodyValue(dto)
                .retrieve()
                .onStatus(status -> status.isError(), response ->
                        response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new RuntimeException("출금 실패: " + body)))
                )
                .bodyToMono(Void.class);
    }

    public Mono<Void> cancelWithdraw(String bankKey, TransferRequestDTO dto) {
        String url = env.getProperty("bank." + bankKey + ".url") + "/" + bankKey + "/cancel-withdraw";
        return webClient.post()
                .uri(url)
                .bodyValue(dto)
                .retrieve()
                .onStatus(status -> status.isError(), response ->
                        response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new RuntimeException("출금 취소 실패: " + body)))
                )
                .bodyToMono(Void.class);
    }
}
