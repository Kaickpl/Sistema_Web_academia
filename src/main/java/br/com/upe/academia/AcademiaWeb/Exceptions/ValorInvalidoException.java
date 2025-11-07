package br.com.upe.academia.AcademiaWeb.Exceptions;

public class ValorInvalidoException extends RuntimeException {
    public ValorInvalidoException(String message) {
        super(message);
    }
    public ValorInvalidoException(){super("Error: Valor inválido");}
}
