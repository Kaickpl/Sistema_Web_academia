package br.com.upe.academia.AcademiaWeb.Exceptions;

public class SenhasNaoConferemException extends RuntimeException {
    public SenhasNaoConferemException(String message) {
        super(message);
    }
    public SenhasNaoConferemException() {
      super("Erro: As senhas não conferem.");
    }
}
