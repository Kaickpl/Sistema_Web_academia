package br.com.upe.academia.AcademiaWeb.Exceptions;

public class EmailInvalidoException extends RuntimeException {
    public EmailInvalidoException(String message) {
        super(message);
    }
    public EmailInvalidoException() {
        super("Error: Email invalido");
    }
}
