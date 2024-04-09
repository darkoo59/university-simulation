package com.example.universitysimulation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class NotFoundInDataBaseException extends RuntimeException{
    private String customMessage;
}
