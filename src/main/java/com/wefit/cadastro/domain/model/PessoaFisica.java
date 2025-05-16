package com.wefit.cadastro.domain.model;

import com.wefit.cadastro.application.factories.Pessoa;
import jakarta.persistence.*;

@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica implements Pessoa {

    private final String tipoPessoa;
    private final String cpf;
    private final String nome;
    private final String celular;
    private final String telefone;
    private final String email;
    private final boolean aceiteTermosdeUso;
    @Embedded
    private final Endereco endereco;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public PessoaFisica(String tipoPessoa, String cpf, String nome, String celular, String telefone, String email, boolean aceiteTermosdeUso, Endereco endereco) {
        this.tipoPessoa = tipoPessoa;
        this.cpf = cpf;
        this.nome = nome;
        this.celular = celular;
        this.telefone = telefone;
        this.email = email;
        this.aceiteTermosdeUso = aceiteTermosdeUso;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
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
        return aceiteTermosdeUso;
    }

    @Override
    public Endereco getEndereco() {
        return endereco;
    }
}
