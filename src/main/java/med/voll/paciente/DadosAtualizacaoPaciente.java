package med.voll.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String telefone,
        String nome,
        DadosEndereco endereco) {
}
