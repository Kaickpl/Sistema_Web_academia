package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

public class ExceptionResponseDTO {
    private LocalDateTime timestamp;
    private String message;
    private int status;
    private String request;

    public ExceptionResponseDTO(String message, int status, String request) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.status = status;
        this.request = request;
    }
}
