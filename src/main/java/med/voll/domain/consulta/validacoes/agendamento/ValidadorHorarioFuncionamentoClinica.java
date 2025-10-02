package med.voll.domain.consulta.validacoes;

import med.voll.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;

@Service
public class ValidadorHorarioFuncionamentoClinica   implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        var dataConsulta = dadosAgendamentoConsulta.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesAbertura = dataConsulta.getHour() < 7;
        var horarioDepoisFechamento = dataConsulta.getHour() > 18;
        if (domingo || horarioAntesAbertura || horarioDepoisFechamento) {
            throw new RuntimeException("Consulta fora do horário de funcionamento da clínica");
        }
    }

}
