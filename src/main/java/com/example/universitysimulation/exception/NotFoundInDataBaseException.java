package com.example.universitysimulation.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class NotFoundInDataBaseException extends RuntimeException{
    private String message;
}
