package com.wefit.cadastro.application.dto.in;

public record EnderecoDto(
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String cidade,
        String bairro,
        String estado) {
}
