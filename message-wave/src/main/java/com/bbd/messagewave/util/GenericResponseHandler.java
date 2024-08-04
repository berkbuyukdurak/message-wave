package com.bbd.messagewave.util;

import com.bbd.messagewave.model.GenericApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenericResponseHandler {
    public static ResponseEntity<Object> successResponse(HttpStatus status, Object data) {
        GenericApiResponse<Object> response = new GenericApiResponse<>(true, data);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<Object> errorResponse(HttpStatus status, String errorMessage) {
        GenericApiResponse<String> response = new GenericApiResponse<>(false, errorMessage);
        return new ResponseEntity<>(response, status);
    }
}
