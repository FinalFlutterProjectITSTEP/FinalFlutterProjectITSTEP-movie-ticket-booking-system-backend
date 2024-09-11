package com.jts.movie.response;
import lombok.Data;

@Data
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;

    // Constructors, Getters, Setters
    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Getters and setters...
}
