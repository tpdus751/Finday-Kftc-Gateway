package com.finday.gateway.kftc_gateway.controller;

import com.finday.gateway.kftc_gateway.dto.AccountDTO;
import com.finday.gateway.kftc_gateway.dto.ResponsedTransactionDTO;
import com.finday.gateway.kftc_gateway.service.TransactionAggregationService;
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
@RequestMapping("/gateway/transaction")
@RequiredArgsConstructor
@Tag(name = "Transaction Gateway", description = "거래 내역 통합 조회 API")
public class TransactionGatewayController {

    private final TransactionAggregationService transactionAggregationService;

    @Operation(summary = "최근 30일 은행 거래 내역 조회", description = "userHash 기반으로 해당 은행의 최근 30일 거래 내역을 조회합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "성공적으로 거래 내역을 반환",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponsedTransactionDTO.class)))
    )
    @GetMapping("/latest30days")
    public ResponseEntity<List<ResponsedTransactionDTO>> requestLatest30days(
            @Parameter(description = "거래 내역을 조회할 영문 은행명") @RequestParam String bankName,
            @Parameter(description = "SHA-256 해시된 사용자 식별자") @RequestParam String userSpecificNo
        ) {

        System.out.println("트랜잭션 요청 들어옴 : " + bankName);

        List<ResponsedTransactionDTO> result = transactionAggregationService.fetchLatest30DayTransaction(bankName, userSpecificNo);
        for (ResponsedTransactionDTO transaction : result) {
            System.out.println(transaction);
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "지정 년월 은행 거래 내역 조회", description = "userHash, month 기반으로 해당 은행의 지정 년월 거래 내역을 조회합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "성공적으로 거래 내역을 반환",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponsedTransactionDTO.class)))
    )
    @GetMapping("/month")
    public ResponseEntity<List<ResponsedTransactionDTO>> requestLatest30days(
            @Parameter(description = "거래 내역을 조회할 영문 은행명") @RequestParam String bankName,
            @Parameter(description = "SHA-256 해시된 사용자 식별자") @RequestParam String userSpecificNo,
            @Parameter(description = "사용자가 지정한 년월 정보") @RequestParam String month
    ) {

        List<ResponsedTransactionDTO> result = transactionAggregationService.fetchMonthTransaction(bankName, userSpecificNo, month);
        for (ResponsedTransactionDTO transaction : result) {
            System.out.println(transaction);
        }
        return ResponseEntity.ok(result);
    }
}
