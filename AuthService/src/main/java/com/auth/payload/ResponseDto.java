package com.auth.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ResponseDto {

    private String message;
    private boolean success;

    public ResponseDto(String message, boolean success) {
        super();
        this.message = message;
        this.success = success;
    }

}
