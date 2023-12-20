package com.example.demo.user.responsedto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDTO {
   private String message;

    @Builder
    private UserResponseDTO(String message){
        this.message = message;
    }
}
