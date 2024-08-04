package com.bbd.messagewave.util;

import com.bbd.messagewave.model.GenericApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenericResponseHandler {
    public static <T> ResponseEntity<GenericApiResponse<T>> successResponse(HttpStatus status, String message, T data) {
        GenericApiResponse<T> response = new GenericApiResponse<>(true, message, data);
        return new ResponseEntity<>(response, status);
    }

    // This method now correctly handles returning a message without specific data.
    public static <T> ResponseEntity<GenericApiResponse<T>> errorResponse(HttpStatus status, String errorMessage) {
        GenericApiResponse<T> response = new GenericApiResponse<>(false, errorMessage, null); // 'null' for no data
        return new ResponseEntity<>(response, status);
    }
}
