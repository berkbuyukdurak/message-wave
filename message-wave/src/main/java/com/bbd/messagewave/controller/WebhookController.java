package com.bbd.messagewave.controller;

import com.bbd.messagewave.model.GenericApiResponse;
import com.bbd.messagewave.model.dto.message.request.WebhookRequestDTO;
import com.bbd.messagewave.model.dto.message.response.SendMessageResponseDTO;
import com.bbd.messagewave.service.message.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final MessageService messageService;

    @Value("${messagewave.message.sender.auth.key}")
    private String webhookAuthKey;

    public WebhookController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/receive")
    @Operation(summary = "Receive a webhook message", description = "Receives a message from webhook and records it in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Message received and created successfully",
                    content = @Content(schema = @Schema(implementation = GenericApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data or missing auth key",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<SendMessageResponseDTO> receiveWebhook(
            @RequestHeader(value = "x-ins-auth-key", required = true) String authKey,
            @RequestBody WebhookRequestDTO payload
    ) {
        // Auth key kontrolü
        if (!webhookAuthKey.equals(authKey)) {
            SendMessageResponseDTO errorResponse = new SendMessageResponseDTO("Unauthorized", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }

        try {
            SendMessageResponseDTO response = messageService.createMessageFromWebhook(payload);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            SendMessageResponseDTO errorResponse = new SendMessageResponseDTO("Failed to create message: " + e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}