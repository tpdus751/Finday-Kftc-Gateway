package com.finday.gateway.kftc_gateway.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "이체 요청 DTO")
public class TransferRequestDTO {
    private String userName;
    private String fromBankName;
    private String fromAccountNumber;
    private String toBankName;
    private String toAccountNumber;
    private Long amount;
    private String note;
    private String senderDisplay;     // 내 통장 표시
    private String receiverDisplay;   // 받는 분 통장 표시
    private String userSpecificNo;
}
