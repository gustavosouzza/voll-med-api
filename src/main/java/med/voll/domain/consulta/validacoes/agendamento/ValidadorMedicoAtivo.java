package med.voll.domain.consulta.validacoes;

import med.voll.domain.consulta.DadosAgendamentoConsulta;
import med.voll.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorMedicoAtivo   implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository repository;
    public void validar(DadosAgendamentoConsulta dados)  {
        if (dados.idMedico() == null) {
            return ;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());

        if (!medicoEstaAtivo) {
            throw new RuntimeException("Consulta não pode ser agendada com médico excluído");
        }
    }
}
