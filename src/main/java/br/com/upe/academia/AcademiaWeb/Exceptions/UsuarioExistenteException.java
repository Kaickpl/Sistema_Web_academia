package br.com.upe.academia.AcademiaWeb.Exceptions;

public class UsuarioExistenteException extends RuntimeException {
    public UsuarioExistenteException(String message) {
        super(message);
    }
    public UsuarioExistenteException() {
        super("Error: Usuario existente");
    }
}
