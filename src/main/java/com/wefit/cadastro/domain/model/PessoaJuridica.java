package com.wefit.cadastro.domain.model;

import com.wefit.cadastro.application.factories.Pessoa;
import jakarta.persistence.*;

@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica implements Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String tipoPessoa;
    private final String cnpj;
    private final String nome;
    private final String celular;
    private final String telefone;
    private final String email;
    private final boolean aceiteTermosDeUso;
    @Embedded
    private final Endereco endereco;

    public PessoaJuridica(String tipoPessoa, String cnpj, String nome, String celular, String telefone, String email, boolean aceiteTermosDeUso, Endereco endereco) {
        this.tipoPessoa = tipoPessoa;
        this.cnpj = cnpj;
        this.nome = nome;
        this.celular = celular;
        this.telefone = telefone;
        this.email = email;
        this.aceiteTermosDeUso = aceiteTermosDeUso;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getCelular() {
        return celular;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }

    @Override
    public boolean getAceiteTermosDeUso() {
        return aceiteTermosDeUso;
    }

    @Override
    public Endereco getEndereco() {
        return endereco;
    }
}
