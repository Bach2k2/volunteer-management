package com.bach2k2.volunteer.dto;

public class RefreshTokenResponseDto {
    private String accessToken;

    public RefreshTokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
