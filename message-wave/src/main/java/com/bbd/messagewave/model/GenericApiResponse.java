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

    @Schema(description = "Error message if request failed")
    private String errorMessage;

    // Constructors for success and error scenarios
    public GenericApiResponse(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public GenericApiResponse(boolean status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

}