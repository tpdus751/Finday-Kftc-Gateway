package com.finday.gateway.kftc_gateway.controller;

import com.finday.gateway.kftc_gateway.dto.AccountDTO;
import com.finday.gateway.kftc_gateway.dto.CardDTO;
import com.finday.gateway.kftc_gateway.service.AccountAggregationService;
import com.finday.gateway.kftc_gateway.service.CardAggregationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gateway/cards")
@RequiredArgsConstructor
@Tag(name = "Card Gateway", description = "은행 카드 통합 조회 API")
public class CardGatewayController {

    private final CardAggregationService cardAggregationService;

    @Operation(summary = "사용자 선택된 은행 카드 조회", description = "userHash 기반으로 선택된 은행의 계좌를 조회합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "성공적으로 카드 목록을 반환",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CardDTO.class)))
    )
    @GetMapping("/list")
    public ResponseEntity<List<CardDTO>> getSelectedBankAccounts(
            @Parameter(description = "SHA-256 해시된 사용자 식별자") @RequestParam String userSpecificNo,
            @Parameter(description = "선택된 은행 이름") @RequestParam String bankName
    ) {
        List<CardDTO> result = cardAggregationService.fetchSelectedBankCards(userSpecificNo, bankName);
        for (CardDTO card : result) {
            System.out.println(card);
        }
        return ResponseEntity.ok(result);
    }

}
