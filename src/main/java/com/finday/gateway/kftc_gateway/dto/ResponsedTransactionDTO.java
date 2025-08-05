package com.finday.gateway.kftc_gateway.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "거래 내역 응답 DTO")
public class ResponsedTransactionDTO {
    @Schema(description = "거래처명", example = "GS25")
    private String transactionName;

    @Schema(description = "거래 유형", example = "출금")
    private String transactionType;

    @Schema(description = "거래 카테고리", example = "식비")
    private String transactionCategory;

    @Schema(description = "거래 내역에 표시될 내용", example = "이체 시에만 유효")
    private String fromAccountDisplay;

    @Schema(description = "거래 내역에 표시될 내용", example = "이체 시에만 유효")
    private String toAccountDisplay;

    @Schema(description = "거래 대금", example = "5500")
    private Long amount;

    @Schema(description = "거래 시각")
    private String createdAt;

    @Schema(description = "계좌 이름")
    private String accountName;

    @Schema(description = "은행명", example = "국민은행")
    private String bankName;

    @Schema(description = "거래에 사용된 카드 번호", example = "111111-11-111111")
    private String cardNumber;

    @Schema(description = "거래에 사용된 카드명", example = "국민카드")
    private String paidCardName;
}
