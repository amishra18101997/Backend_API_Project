package com.microservices.gateway.ApiGateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String userId;
    private String accessToken;

    //private String refreshToken;

    private Long expiresAt;

    private Collection<String> authorities;

}
