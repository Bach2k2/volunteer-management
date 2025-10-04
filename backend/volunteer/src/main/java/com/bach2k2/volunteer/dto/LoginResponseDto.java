package com.bach2k2.volunteer.dto;
// import lombok.Builder;
// import lombok.Data;

// @Data
// @Builder
public class LoginResponseDto {
    private String token;

    public LoginResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
