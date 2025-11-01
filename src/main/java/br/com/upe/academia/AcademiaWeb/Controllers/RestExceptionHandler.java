package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExceptionResponseDTO;
import br.com.upe.academia.AcademiaWeb.Exceptions.*;
import jakarta.persistence.ElementCollection;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(UsuarioExistenteException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUsuarioExistenteException(UsuarioExistenteException ex, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ex.getMessage(), 409, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(CampoObrigatorioException.class)
    public ResponseEntity<String> handleCampoObrigatorioException(CampoObrigatorioException ex, HttpServletRequest request) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex, HttpServletRequest request) {
        ExceptionResponseDTO  exceptionResponseDTO = new ExceptionResponseDTO(ex.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(EmailInvalidoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleEmailInvalidoException(EmailInvalidoException ex, HttpServletRequest request) {
        ExceptionResponseDTO  exceptionResponseDTO = new ExceptionResponseDTO(ex.getMessage(), 422, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(SenhasNaoConferemException.class)
    public ResponseEntity<ExceptionResponseDTO> handleSenhasNaoConferemException(SenhasNaoConferemException ex, HttpServletRequest request) {
        ExceptionResponseDTO  exceptionResponseDTO = new ExceptionResponseDTO(ex.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    public ResponseEntity<ExceptionResponseDTO> handleOperacaoNaoPermitida(OperacaoNaoPermitidaException ex, HttpServletRequest request) {
        ExceptionResponseDTO  exceptionResponseDTO = new ExceptionResponseDTO(ex.getMessage(), 403, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }
}
