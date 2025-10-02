package med.voll.domain.consulta.validacoes;

import med.voll.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Service;

@Service
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {
    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        var dataConsulta = dadosAgendamentoConsulta.data();
        var agora = java.time.LocalDateTime.now();

        var diferencaEmMinutos = java.time.Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new RuntimeException("Consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }
}
