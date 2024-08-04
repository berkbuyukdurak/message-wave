package com.bbd.messagewave.controller;

import com.bbd.messagewave.model.GenericApiResponse;
import com.bbd.messagewave.model.dto.message.response.GetAllMessagesResponseDTO;
import com.bbd.messagewave.model.dto.message.response.GetAllSentMessagesResponseDTO;
import com.bbd.messagewave.model.entity.Message;
import com.bbd.messagewave.service.MessageService;
import com.bbd.messagewave.util.GenericResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @Operation(summary = "Get All Messages", description = "Returns all the messages stored in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of messages",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericApiResponse.class))),
            @ApiResponse(responseCode = "404", description = "No messages found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericApiResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericApiResponse.class)))
    })
    public ResponseEntity<GenericApiResponse<List<GetAllMessagesResponseDTO>>> getAllMessages() {
        try {
            List<GetAllMessagesResponseDTO> messages = messageService.getAllMessages();
            if (messages.isEmpty()) {
                return GenericResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Messages not found");
            }
            return GenericResponseHandler.successResponse(HttpStatus.OK,"Successfully retrieved messages", messages);
        } catch (Exception e) {
            return GenericResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error: " + e.getMessage());
        }
    }

    @GetMapping("/sent")
    @Operation(summary = "Retrieve Sent Messages", description = "Retrieves a list of messages that have been sent and cached.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved sent messages",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericApiResponse.class))),
            @ApiResponse(responseCode = "404", description = "No sent messages found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericApiResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericApiResponse.class)))
    })
    public ResponseEntity<GenericApiResponse<List<GetAllSentMessagesResponseDTO>>> getAllSentMessages() {
        try {
            List<GetAllSentMessagesResponseDTO> sentMessages = messageService.getAllSentMessages();
            if (sentMessages.isEmpty()) {
                return GenericResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "No sent messages found");
            }
            return GenericResponseHandler.successResponse(HttpStatus.OK, "Successfully retrieved sent messages", sentMessages);
        } catch (Exception e) {
            return GenericResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error: " + e.getMessage());
        }
    }
}
