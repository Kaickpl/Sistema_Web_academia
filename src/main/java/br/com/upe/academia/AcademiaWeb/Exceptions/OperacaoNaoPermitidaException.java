package br.com.upe.academia.AcademiaWeb.Exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException {
    public OperacaoNaoPermitidaException(String message) {
        super(message);
    }
    public OperacaoNaoPermitidaException() {
        super("Erro: Operação não permitida.");
    }
}
