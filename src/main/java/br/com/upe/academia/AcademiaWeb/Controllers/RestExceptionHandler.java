package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExceptionResponseDTO;
import br.com.upe.academia.AcademiaWeb.Exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(UsuarioExistenteException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUsuarioExistenteException(UsuarioExistenteException ex, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ex.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(CampoObrigatorioException.class)
    public ResponseEntity<String> handleCampoObrigatorioException(CampoObrigatorioException ex, HttpServletRequest request) {
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

    @ExceptionHandler(ValorInvalidoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleMedidaInvalidaException(ValorInvalidoException ex, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ex.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler (NullPointerException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCampoVazio(NullPointerException ex, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Erro de campo vazio: Verifique se todos os campos obrigatórios foram devidamente preenchidos.",
                400, request.getRequestURI());
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
                    "Erro: algum dos campos está com o formato inválido",
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

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ExceptionResponseDTO> usuarioNaoEncontradoException(UsuarioNaoEncontradoException ex, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                "Usuário não encontrado",
                404,
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseDTO);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponseDTO> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request){
        String causa = "";
        if (ex.getRootCause() != null){
            causa = ex.getRootCause().getMessage();
        } else {
            causa = "Violação de integridade de dados (verifique os campos obrigatórios).";
        }
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Erro de dados no banco: " + causa,
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseDTO);
    }

    @ExceptionHandler(ConquistaRegistradaException.class)
    public ResponseEntity<ExceptionResponseDTO> handleConquistaRegistradaException(ConquistaRegistradaException ex, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                ex.getMessage(),
                400,
                request.getRequestURI()
        );
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }
}
