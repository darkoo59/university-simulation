package com.example.universitysimulation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class AlreadyExistInDataBaseException extends RuntimeException {
    private String customMessage;
}
