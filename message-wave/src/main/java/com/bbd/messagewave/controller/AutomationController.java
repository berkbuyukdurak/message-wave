package com.bbd.messagewave.controller;


import com.bbd.messagewave.model.GenericApiResponse;
import com.bbd.messagewave.service.automation.AutomationService;
import com.bbd.messagewave.util.GenericResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/automation")
public class AutomationController {

    private final AutomationService automationService;

    public AutomationController(AutomationService automationService) {
        this.automationService = automationService;
    }

    @Operation(summary = "Start Automation", description = "Starts the automated message processing")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Automation started successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericApiResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error starting automation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericApiResponse.class)))
    })
    @GetMapping("/start")
    public ResponseEntity<GenericApiResponse<String>> startAutomation() {
        try {
            automationService.startProcessing();
            return GenericResponseHandler.successResponse(org.springframework.http.HttpStatus.OK, "Automation started successfully", null);
        } catch (Exception e) {
            return GenericResponseHandler.errorResponse(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, "Error starting automation: " + e.getMessage());
        }
    }

    @Operation(summary = "Stop Automation", description = "Stops the automated message processing")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Automation stopped successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericApiResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error stopping automation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericApiResponse.class)))
    })
    @GetMapping("/stop")
    public ResponseEntity<GenericApiResponse<String>> stopAutomation() {
        try {
            automationService.stopProcessing();
            return GenericResponseHandler.successResponse(org.springframework.http.HttpStatus.OK, "Automation stopped successfully", null);
        } catch (Exception e) {
            return GenericResponseHandler.errorResponse(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, "Error stopping automation: " + e.getMessage());
        }
    }
}
