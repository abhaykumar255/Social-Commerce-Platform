package com.social.user.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private String tokenType;
    private long expiresIn;
    private UUID userId;
    private String email;
    private String firstName;
    private String lastName;

}
