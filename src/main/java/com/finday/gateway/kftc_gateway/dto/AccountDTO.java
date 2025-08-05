package com.finday.gateway.kftc_gateway.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "계좌 응답 DTO")
public class AccountDTO {

    @Schema(description = "계좌 번호", example = "11-1111-1111111")
    private String accountNumber;

    @Schema(description = "별칭", example = "월급통장")
    private String alias;

    @Schema(description = "잔액", example = "1200000")
    private Long balance;

    @Schema(description = "생성일시", example = "2024-01-01T12:00:00")
    private String createdAt;

    @Schema(description = "은행명", example = "국민은행")
    private String bankName;

    @Schema(description = "계좌 이름", example = "KB 별별통장")
    private String accountName;
}