package br.com.treinaweb.ediaristas.core.exeptions;

import jakarta.persistence.EntityNotFoundException;

public class UsuarioNaoEncontradoException extends  EntityNotFoundException {
    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }
}
