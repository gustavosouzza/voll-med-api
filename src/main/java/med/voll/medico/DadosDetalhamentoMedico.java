package med.voll.medico;

import med.voll.endereco.DadosEndereco;
import med.voll.endereco.Endereco;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco

) {
    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(),  medico.getTelefone() ,medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
