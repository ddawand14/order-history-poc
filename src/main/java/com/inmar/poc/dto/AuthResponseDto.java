package com.inmar.poc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthResponseDto {
   private String accessToken;
   private long expiresIn;


}
