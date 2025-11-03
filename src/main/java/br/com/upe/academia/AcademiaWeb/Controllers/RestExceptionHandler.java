package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExceptionResponseDTO;
import br.com.upe.academia.AcademiaWeb.Exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(UsuarioExistenteException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUsuarioExistenteException(UsuarioExistenteException ex, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ex.getMessage(), 409, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(CampoObrigatorioException.class)
    public ResponseEntity<String> handleCampoObrigatorioException(CampoObrigatorioException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(InformacaoNaoEncontradoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUsuarioNaoEncontradoException(InformacaoNaoEncontradoException ex, HttpServletRequest request) {
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
    @ExceptionHandler(ValorNuloNaoPermitidoException.class)
    public ResponseEntity<String> handleValorNuloNaoPermitidoException(ValorNuloNaoPermitidoException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(CrefInvalidoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCrefInvalidoException(CrefInvalidoException ex, HttpServletRequest request) {
        ExceptionResponseDTO   exceptionResponseDTO = new ExceptionResponseDTO(ex.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponseDTO> handleJsonParseError(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO;

        if (ex.getCause() instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException) {
            exceptionResponseDTO = new ExceptionResponseDTO(
                    "Formato inválido: o campo 'idUsuario' deve ser um UUID válido (ex: 550e8400-e29b-41d4-a716-446655440000)",
                    400,
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseDTO);
        }
        exceptionResponseDTO = new ExceptionResponseDTO(
                "Erro ao ler o corpo da requisição. Verifique o formato JSON.",
                400,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseDTO);
    }
}
