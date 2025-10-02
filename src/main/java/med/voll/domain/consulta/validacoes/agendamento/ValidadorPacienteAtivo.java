package med.voll.domain.consulta.validacoes;

import med.voll.domain.ValidacaoException;
import med.voll.domain.consulta.DadosAgendamentoConsulta;
import med.voll.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ValidadorPacienteAtivo   implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private PacienteRepository pacienteRepository;
    public void validar(DadosAgendamentoConsulta dados){
        var pacienteAtivo = pacienteRepository.FindAtivoById(dados.idPaciente());
        if (!pacienteAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
