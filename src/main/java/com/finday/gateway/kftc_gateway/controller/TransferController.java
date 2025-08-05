package com.finday.gateway.kftc_gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finday.gateway.kftc_gateway.dto.ResponsedTransactionDTO;
import com.finday.gateway.kftc_gateway.dto.TransferRequestDTO;
import com.finday.gateway.kftc_gateway.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway/transfer")
@RequiredArgsConstructor
@Tag(name = "Transfer Gateway", description = "이체 요청을 보내는 중계 API")
public class TransferController {

    private final TransferService transferService;

    @Operation(summary = "이체 요청", description = "String으로 전송된 JSON 데이터를 역직렬화하여 DTO에 매핑시킵니다.")
    @ApiResponse(
            responseCode = "200",
            description = "이체 성공 문자열을 보냅니다.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))
    )
    @PostMapping
    public ResponseEntity<String> transfer(@RequestBody String rawBody) {
        System.out.println("RAW BODY: " + rawBody);
        try {
            ObjectMapper mapper = new ObjectMapper();
            TransferRequestDTO request = mapper.readValue(rawBody, TransferRequestDTO.class);
            transferService.transfer(request);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("FAILED: " + e.getMessage());
        }
    }
}
