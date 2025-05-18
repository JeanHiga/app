package com.wefit.cadastro.controller;

import com.wefit.cadastro.application.dto.in.PessoaDto;
import com.wefit.cadastro.application.service.CadastroPessoaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cadastro")
public class CadastroController {

    private static final Logger logger = LoggerFactory.getLogger(CadastroController.class);

    private final CadastroPessoaService cadastroPessoaService;

    public CadastroController(CadastroPessoaService cadastroPessoaService) {
        this.cadastroPessoaService = cadastroPessoaService;
    }

    @PostMapping
    public ResponseEntity<String> cadastrarPessoa(@Valid @RequestBody PessoaDto pessoaDto) {

        try {
            cadastroPessoaService.cadastrar(pessoaDto);
            logger.info("Pessoa cadastrada com sucesso");
            return new ResponseEntity<>("Pessoa Criada com sucesso",HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.error("Erro em cadastrar a pessoa: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            logger.error("Erro ao cadastrar pessoa: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
