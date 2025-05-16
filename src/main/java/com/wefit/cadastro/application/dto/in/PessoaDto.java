package com.wefit.cadastro.application.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public record PessoaDto(
        @NotNull(message = "Tipo pessoa é obrigatório")
        @Pattern(regexp = "(?i)^(fisica|juridica)$", message = "Tipo pessoa deve ser FISICA ou JURIDICA")
        @JsonProperty("tipo_pessoa")
        String tipoPessoa,

        @CNPJ
        @JsonProperty("cnpj")
        String cnpj,

        @CPF
        @JsonProperty("cpf")
        String cpf,

        @NotBlank(message = "Nome é obrigatório")
        @JsonProperty("nome")
        String nome,

        @NotBlank(message = "Celular é obrigatório")
        @Pattern(regexp = "^\\d{10,11}$", message = "Celular inválido")
        @JsonProperty("celular")
        String celular,

        @Pattern(regexp = "^\\d{10,11}$", message = "Telefone inválido")
        @JsonProperty("telefone")
        String telefone,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        @JsonProperty("email")
        String email,

        @NotBlank(message = "Confirmação de email é obrigatória")
        @JsonProperty("confirmar_email")
        String confirmarEmail,

        @Valid
        @NotNull(message = "Endereço é obrigatório")
        @JsonProperty("endereco")
        EnderecoDto endereco,

        @JsonProperty("aceite_termos_uso")
        @AssertTrue(message = "O aceite com os termos de uso é obrigatório")
        boolean aceiteTermosDeUso) {

    // Validação customizada para CPF/CNPJ
    @AssertTrue(message = "CPF obrigatório para pessoa física")
    private boolean isCpfValidoParaPessoaFisica() {
        if ("FISICA".equals(tipoPessoa)) {
            return cpf != null && !cpf.trim().isEmpty();
        }
        return true;
    }

    @AssertTrue(message = "CNPJ obrigatório para pessoa jurídica")
    private boolean isCnpjValidoParaPessoaJuridica() {
        if ("JURIDICA".equals(tipoPessoa)) {
            return cnpj != null && !cnpj.trim().isEmpty();
        }
        return true;
    }

    @AssertTrue(message = "Email e confirmação de email devem ser iguais")
    private boolean isEmailConfirmacaoValida() {
        return email != null && email.equals(confirmarEmail);
    }
}