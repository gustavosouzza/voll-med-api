package med.voll.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String telefone,
        String nome,
        DadosEndereco endereco) {

}
