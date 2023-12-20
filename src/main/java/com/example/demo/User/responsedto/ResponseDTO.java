package com.example.demo.User.responsedto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseDTO {
   private String message;

    @Builder
    private ResponseDTO (String message){
        this.message = message;
    }
}
