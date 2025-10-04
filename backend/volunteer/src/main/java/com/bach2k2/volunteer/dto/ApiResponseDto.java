package com.bach2k2.volunteer.dto;

public class ApiResponseDto<T> {
    private int status;
    private String message;
    private T response;
    public ApiResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
    public ApiResponseDto(int status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

     public int getStatus() {
        return status;
    }   
    public void setStatus(int status) {
        this.status = status;
    }
}
    
