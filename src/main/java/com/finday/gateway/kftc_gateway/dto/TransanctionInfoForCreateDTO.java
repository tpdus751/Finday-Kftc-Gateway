package com.finday.gateway.kftc_gateway.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "은행서버에 거래 내역을 생성하기 위한 DTO")
public class TransanctionInfoForCreateDTO {

    private String merchant;

    private Long amount;

    private String category;

    private String bankName;

    private String methodType;

    private String methodId;

    private String userSpecificNo;
}
