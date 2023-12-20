package com.example.demo.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommonResponseDTO {
    String msg;
    int statusCode;
}
