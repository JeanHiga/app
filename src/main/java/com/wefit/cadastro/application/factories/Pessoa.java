package com.wefit.cadastro.application.factories;

import com.wefit.cadastro.domain.model.Endereco;

public interface Pessoa {
    String getTipoPessoa();
    String getNome();
    String getCelular();
    String getEmail();
    String getTelefone();
    boolean getAceiteTermosDeUso();
    Endereco getEndereco();
}
