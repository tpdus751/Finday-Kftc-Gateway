package com.finday.gateway.kftc_gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finday.gateway.kftc_gateway.dto.CardDTO;
import com.finday.gateway.kftc_gateway.dto.TransanctionInfoForCreateDTO;
import com.finday.gateway.kftc_gateway.dto.requestedPaymentDTO;
import com.finday.gateway.kftc_gateway.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway/pay")
@RequiredArgsConstructor
@Tag(name = "Pay Gateway", description = "은행 결제 API")
public class PaymentGatewayController {

    private final PaymentService paymentService;

    @Operation(summary = "계좌 또는 카드로의 통합 계산을 요청", description = "String 기반의 JSON 데이터를 DTO에 매핑시킵니다.")
    @ApiResponse(
            responseCode = "200",
            description = "요청 성공 메시지를 전달",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))
    )
    @PostMapping("/intergrated-method")
    public ResponseEntity<String> paymentRequestToBankByAccount(@RequestBody String rawBody) {
        System.out.println("RAW BODY: " + rawBody);
        try {
            ObjectMapper mapper = new ObjectMapper();
            TransanctionInfoForCreateDTO request = mapper.readValue(rawBody, TransanctionInfoForCreateDTO.class);

            requestedPaymentDTO response = new requestedPaymentDTO();

            response.setPlaceOfUse(request.getMerchant());
            response.setTransactionType("출금");
            response.setAmount(request.getAmount());
            response.setBankName(request.getBankName());
            response.setCategory(request.getCategory());
            response.setMethodType(request.getMethodType());
            response.setMethodId(request.getMethodId());
            response.setUserSpecificNo(request.getUserSpecificNo());

            System.out.println("2 : " + response);

            paymentService.pay(response);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("FAILED: " + e.getMessage());
        }

    }
}
