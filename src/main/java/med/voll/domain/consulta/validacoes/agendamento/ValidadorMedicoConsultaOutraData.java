package med.voll.domain.consulta.validacoes.agendamento;

import med.voll.domain.ValidacaoException;
import med.voll.domain.consulta.ConsultaRepository;
import med.voll.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorMedicoConsultaOutraData implements ValidadorAgendamentoDeConsulta {
  @Autowired private ConsultaRepository repository;

  public void validar(DadosAgendamentoConsulta dados) {
    var medicoPossuiOutraConsultaNaHora =
        repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(
            dados.idMedico(), dados.data());
    if (medicoPossuiOutraConsultaNaHora) {
      throw new ValidacaoException("Médico já possui outra consulta agendada nessa data");
    }
  }
}
