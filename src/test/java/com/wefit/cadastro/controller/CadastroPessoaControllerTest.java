package com.wefit.cadastro.controller;

import com.wefit.cadastro.application.dto.in.PessoaDto;
import com.wefit.cadastro.application.service.CadastroPessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CadastroPessoaControllerTest {

    private CadastroPessoaService cadastroPessoaService;
    private CadastroController controller;

    @BeforeEach
    public void setUp() {
        cadastroPessoaService = mock(CadastroPessoaService.class);
        controller = new CadastroController(cadastroPessoaService);
    }

    @Test
    public void deveRetornar201QuandoCadastroForSucesso() {
        PessoaDto pessoaDto = mock(PessoaDto.class);

        ResponseEntity<String> response = controller.cadastrarPessoa(pessoaDto);

        verify(cadastroPessoaService, times(1)).cadastrar(pessoaDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Pessoa Criada com sucesso", response.getBody());
    }

    @Test
    public void deveRetornar422QuandoOcorrerRuntimeException() {
        PessoaDto pessoaDto = mock(PessoaDto.class);
        doThrow(new RuntimeException("Cpf já cadastrado")).when(cadastroPessoaService).cadastrar(pessoaDto);

        ResponseEntity<String> response = controller.cadastrarPessoa(pessoaDto);

        verify(cadastroPessoaService, times(1)).cadastrar(pessoaDto);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals("Cpf já cadastrado", response.getBody());
    }
}
