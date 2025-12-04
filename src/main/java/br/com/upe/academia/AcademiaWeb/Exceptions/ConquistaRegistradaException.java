package br.com.upe.academia.AcademiaWeb.Exceptions;

import java.util.UUID;

public class ConquistaRegistradaException extends RuntimeException {
    public ConquistaRegistradaException(String message) {
        super(message);
    }
    public ConquistaRegistradaException(UUID idUsuario, String nomeConquista){
        super("Erro: O usuário com o UUID: " + idUsuario + " já possui a conquista: " + nomeConquista);
    }
}
