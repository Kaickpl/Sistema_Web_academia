package br.com.upe.academia.AcademiaWeb.Exceptions;

public class InformacaoNaoEncontradoException extends RuntimeException {
    public InformacaoNaoEncontradoException(String message) {
        super(message);
    }
    public InformacaoNaoEncontradoException() {
        super("Usuario não encontrado");
    }
}
