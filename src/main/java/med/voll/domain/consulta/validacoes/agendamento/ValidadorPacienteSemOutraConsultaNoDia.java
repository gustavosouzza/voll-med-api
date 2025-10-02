package med.voll.domain.consulta.validacoes.agendamento;

import med.voll.domain.consulta.ConsultaRepository;
import med.voll.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {
  @Autowired private ConsultaRepository repository;

  public void validar(DadosAgendamentoConsulta dados) {
    var primeiroHorario = dados.data().withHour(7);
    var ultimoHorario = dados.data().withHour(18);
    var pacientePossuiOutraConsultaNoDia =
        repository.existsByPacienteIdAndDataBetween(
            dados.idPaciente(), primeiroHorario, ultimoHorario);
    if (pacientePossuiOutraConsultaNoDia) {
      throw new RuntimeException("Paciente já possui outra consulta agendada nesse dia");
    }
  }
}
