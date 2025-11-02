package br.com.upe.academia.AcademiaWeb.Exceptions;

public class CrefInvalidoException extends RuntimeException {
    public CrefInvalidoException(String message) {
        super(message);
    }
    public CrefInvalidoException() {
        super("Error: Cref invalido");
    }
}
