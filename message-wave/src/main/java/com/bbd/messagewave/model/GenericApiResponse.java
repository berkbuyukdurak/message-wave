package com.bbd.messagewave.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Generic API response")
public class GenericApiResponse<T> {
    // Getters and Setters
    @Schema(description = "Indicates if the request was successful")
    private boolean status;

    @Schema(description = "Holds the data if request was successful")
    private T data;

    @Schema(description = "Message describing the success or error")
    private String message;

    // Constructors for success and error scenarios
    public GenericApiResponse(boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public GenericApiResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}