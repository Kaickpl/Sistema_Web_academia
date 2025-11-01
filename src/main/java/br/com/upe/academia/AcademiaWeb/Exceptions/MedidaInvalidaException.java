package br.com.upe.academia.AcademiaWeb.Exceptions;

public class MedidaInvalidaException extends RuntimeException {
    public MedidaInvalidaException(String message) {
        super(message);
    }
    public MedidaInvalidaException(){super("Error: Medida inválida");}
}
