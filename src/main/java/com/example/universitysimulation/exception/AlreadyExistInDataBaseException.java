package com.example.universitysimulation.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AlreadyExistInDataBaseException extends RuntimeException{
    private String message;
}
