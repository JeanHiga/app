package com.wefit.cadastro.application.service;

import com.wefit.cadastro.application.dto.in.EnderecoDto;
import com.wefit.cadastro.application.dto.in.PessoaDto;
import com.wefit.cadastro.domain.model.PessoaFisica;
import com.wefit.cadastro.domain.model.PessoaJuridica;
import com.wefit.cadastro.infraestructure.repository.PessoaFisicaRepository;
import com.wefit.cadastro.infraestructure.repository.PessoaJuridicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CadastroPessoaServiceTest {

    private PessoaFisicaRepository pessoaFisicaRepository;
    private PessoaJuridicaRepository pessoaJuridicaRepository;
    private CadastroPessoaService service;

    @BeforeEach
    public void setUp() {
        pessoaFisicaRepository = mock(PessoaFisicaRepository.class);
        pessoaJuridicaRepository = mock(PessoaJuridicaRepository.class);
        service = new CadastroPessoaService(pessoaFisicaRepository, pessoaJuridicaRepository);
    }

    private PessoaDto criarPessoaFisicaDto() {
        EnderecoDto enderecoDto = new EnderecoDto("12345678", "Rua X", "123", null, "Cidade", "Bairro", "SP");
        return new PessoaDto("FISICA", "12345678901", null, "João", "11999999999", null,
                "joao@email.com", "joao@email.com", enderecoDto, true);
    }

    private PessoaDto criarPessoaJuridicaDto() {
        EnderecoDto enderecoDto = new EnderecoDto("12345678", "Av Y", "456", null, "Cidade", "Bairro", "SP");
        return new PessoaDto("JURIDICA", null, "12345678000190", "Empresa X", "11999999999", null,
                "empresa@email.com", "empresa@email.com", enderecoDto, true);
    }

    @Test
    public void deveCadastrarPessoaFisicaComSucesso() {
        PessoaDto dto = criarPessoaFisicaDto();
        when(pessoaFisicaRepository.existsByCpf(dto.cpf())).thenReturn(false);

        assertDoesNotThrow(() -> service.cadastrar(dto));
        verify(pessoaFisicaRepository, times(1)).save(any(PessoaFisica.class));
    }

    @Test
    public void deveLancarExcecaoSeCpfJaExistir() {
        PessoaDto dto = criarPessoaFisicaDto();
        when(pessoaFisicaRepository.existsByCpf(dto.cpf())).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.cadastrar(dto));
        assertEquals("Cpf ja cadastrado", ex.getMessage());
        verify(pessoaFisicaRepository, never()).save(any());
    }

    @Test
    public void deveCadastrarPessoaJuridicaComSucesso() {
        PessoaDto dto = criarPessoaJuridicaDto();
        when(pessoaJuridicaRepository.existsByCnpj(dto.cnpj())).thenReturn(false);

        assertDoesNotThrow(() -> service.cadastrar(dto));
        verify(pessoaJuridicaRepository, times(1)).save(any(PessoaJuridica.class));
    }

    @Test
    public void deveLancarExcecaoSeCnpjJaExistir() {
        PessoaDto dto = criarPessoaJuridicaDto();
        when(pessoaJuridicaRepository.existsByCnpj(dto.cnpj())).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.cadastrar(dto));
        assertEquals("Cnpj ja cadastrado", ex.getMessage());
        verify(pessoaJuridicaRepository, never()).save(any());
    }
}
