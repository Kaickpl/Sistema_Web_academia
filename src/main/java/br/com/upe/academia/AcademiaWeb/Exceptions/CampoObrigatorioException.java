package br.com.upe.academia.AcademiaWeb.Exceptions;

public class CampoObrigatorioException extends RuntimeException {
    public CampoObrigatorioException(String message) {
        super(message);
    }
    public CampoObrigatorioException() {
        super("Erro: Campo obrigatório não informado.");
    }
}
