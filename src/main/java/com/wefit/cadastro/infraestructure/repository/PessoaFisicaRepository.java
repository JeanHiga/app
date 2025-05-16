package com.wefit.cadastro.infraestructure.repository;

import com.wefit.cadastro.domain.model.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica,Long> {
    boolean existsByCpf(String cpf);
}
