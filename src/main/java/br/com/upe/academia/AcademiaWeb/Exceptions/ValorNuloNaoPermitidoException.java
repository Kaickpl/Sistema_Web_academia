package br.com.upe.academia.AcademiaWeb.Exceptions;

public class ValorNuloNaoPermitidoException extends RuntimeException {
    public ValorNuloNaoPermitidoException(String message) {
        super(message);
    }
    public ValorNuloNaoPermitidoException() {
        super("Este campo não pode ser editado como nulo.");
    }
}
