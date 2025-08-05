package com.finday.gateway.kftc_gateway.client;

import com.finday.gateway.kftc_gateway.dto.requestedPaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BankPaymentClient {

    private final WebClient webClient;
    private final Environment env;

    public Mono<Void> requestPayToBank(requestedPaymentDTO responseDTO) {
        String url = env.getProperty("bank." + responseDTO.getBankName() + ".url") + "/" + responseDTO.getBankName() + "/pay";

        System.out.println(responseDTO.toString());

        System.out.println("3. url : " + url);

        return webClient.post()
                .uri(url)
                .bodyValue(responseDTO)
                .retrieve()
                .onStatus(status -> status.isError(), response ->
                        response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new RuntimeException("결제 실패: " + body)))
                )
                .bodyToMono(Void.class);
    }
}
