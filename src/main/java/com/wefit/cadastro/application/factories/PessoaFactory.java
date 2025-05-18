package com.wefit.cadastro.application.factories;

import com.wefit.cadastro.application.dto.in.PessoaDto;
import com.wefit.cadastro.domain.model.Endereco;
import com.wefit.cadastro.domain.model.PessoaFisica;
import com.wefit.cadastro.domain.model.PessoaJuridica;

public class PessoaFactory {
    public static Pessoa criarPessoa(PessoaDto pessoaDto) {
        Endereco endereco = new Endereco(
                pessoaDto.endereco().cep(),
                pessoaDto.endereco().logradouro(),
                pessoaDto.endereco().numero(),
                pessoaDto.endereco().complemento(),
                pessoaDto.endereco().cidade(),
                pessoaDto.endereco().bairro(),
                pessoaDto.endereco().estado());

        return switch (pessoaDto.tipoPessoa().toUpperCase()) {
            case "FISICA" -> new PessoaFisica(
                    pessoaDto.tipoPessoa(),
                    pessoaDto.cpf(),
                    pessoaDto.nome(),
                    pessoaDto.celular(),
                    pessoaDto.telefone(),
                    pessoaDto.email(),
                    pessoaDto.aceiteTermosDeUso(),
                    endereco);
            case "JURIDICA" -> new PessoaJuridica(
                    pessoaDto.tipoPessoa(),
                    pessoaDto.cnpj(),
                    pessoaDto.nome(),
                    pessoaDto.celular(),
                    pessoaDto.telefone(),
                    pessoaDto.email(),
                    pessoaDto.aceiteTermosDeUso(),
                    endereco);
            default -> throw new IllegalArgumentException("Tipo de pessoa inválido: " + pessoaDto.tipoPessoa());
        };
    }
}
