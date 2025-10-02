package med.voll.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(
    @NotBlank(message = "Nome é obrigatório") String nome,
    @NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email,
    @NotBlank(message = "Telefone é obrigatório") String telefone,
    @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "CPF inválido. Deve conter 11 dígitos")
        String cpf,
    @NotNull(message = "Endereço é obrigatório") @Valid DadosEndereco endereco) {}
