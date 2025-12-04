package br.com.upe.academia.AcademiaWeb.Exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }
    public UsuarioNaoEncontradoException(){super("Error: Usuário não encontrado");}
}
