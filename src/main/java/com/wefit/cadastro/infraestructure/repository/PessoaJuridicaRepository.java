package com.wefit.cadastro.infraestructure.repository;

import com.wefit.cadastro.domain.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica,Long> {
    boolean existsByCnpj(String cnpj);
}
