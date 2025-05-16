package com.wefit.cadastro.application.service;

import com.wefit.cadastro.application.dto.in.PessoaDto;
import com.wefit.cadastro.application.factories.Pessoa;
import com.wefit.cadastro.application.factories.PessoaFactory;
import com.wefit.cadastro.domain.model.PessoaFisica;
import com.wefit.cadastro.domain.model.PessoaJuridica;
import com.wefit.cadastro.infraestructure.repository.PessoaFisicaRepository;
import com.wefit.cadastro.infraestructure.repository.PessoaJuridicaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CadastroPessoaService {

    private static final Logger logger = LoggerFactory.getLogger(CadastroPessoaService.class);

    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    public CadastroPessoaService(PessoaFisicaRepository pessoaFisicaRepository, PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    public void cadastrar(PessoaDto pessoaDto) {
        Pessoa pessoa = PessoaFactory.criarPessoa(pessoaDto);
        if (pessoa instanceof PessoaFisica) {
            cadastrarPessoaFisica((PessoaFisica) pessoa);
        } else if (pessoa instanceof PessoaJuridica) {
            cadastrarPessoaJuridica((PessoaJuridica) pessoa);
        } else {
            logger.error("Tipo de pessoa desconhecido: {}", pessoaDto.tipoPessoa());
            throw new IllegalArgumentException("Tipo de pessoa desconhecido");
        }
    }

    @Transactional
    private void cadastrarPessoaFisica(PessoaFisica pessoaFisica) {
        if (pessoaFisicaRepository.existsByCpf(pessoaFisica.getCpf())) {
            logger.error("CPF já cadastrado");
            throw new IllegalArgumentException("Cpf ja cadastrado");
        }
        logger.info("Salvando pessoa fisica");
        pessoaFisicaRepository.save(pessoaFisica);
    }

    @Transactional
    private void cadastrarPessoaJuridica(PessoaJuridica pessoaJuridica) {
        if (pessoaJuridicaRepository.existsByCnpj(pessoaJuridica.getCnpj())) {
            logger.error("CNPJ já cadastrado");
            throw new IllegalArgumentException("Cnpj ja cadastrado");
        }
        logger.info("Salvando pessoa juridica");
        pessoaJuridicaRepository.save(pessoaJuridica);
    }
}
