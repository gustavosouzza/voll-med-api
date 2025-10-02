package med.voll.domain.consulta;

import jakarta.validation.ValidationException;
import java.util.List;
import med.voll.domain.ValidacaoException;
import med.voll.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.domain.medico.Medico;
import med.voll.domain.medico.MedicoRepository;
import med.voll.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

  @Autowired private ConsultaRepository consultaRepository;

  @Autowired private MedicoRepository medicoRepository;

  @Autowired private PacienteRepository pacienteRepository;

  @Autowired private List<ValidadorAgendamentoDeConsulta> validadores;

  @Autowired private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

  public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
    if (!pacienteRepository.existsById(dados.idPaciente())) {
      throw new ValidacaoException("Id do paciente informado não existe!");
    }
    if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
      throw new ValidacaoException("Id do médico informado não existe!");
    }

    validadores.forEach(v -> v.validar(dados));

    var medico = escolherMedico(dados);
    var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
    var consulta = new Consulta(null, medico, paciente, dados.data(), null);

    if (medico == null) {
      throw new ValidationException("Não existe médico disponível nessa data");
    }

    consultaRepository.save(consulta);
    return new DadosDetalhamentoConsulta(consulta);
  }

  public void cancelar(DadosCancelamentoConsulta dados) {
    if (!consultaRepository.existsById(dados.idConsulta())) {
      throw new ValidacaoException("Id da consulta informado não existe!");
    }
    validadoresCancelamento.forEach(v -> v.validar(dados));

    var consulta = consultaRepository.getReferenceById(dados.idConsulta());
    consulta.cancelar(dados.motivo());
  }

  private Medico escolherMedico(DadosAgendamentoConsulta dados) {
    if (dados.idMedico() != null) {
      return medicoRepository.getReferenceById(dados.idMedico());
    }
    if (dados.especialidade() == null) {
      throw new ValidacaoException(
          "Especialidade do médico é obrigatório quando não for escolhido um médico específico!");
    }

    return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
  }
}
