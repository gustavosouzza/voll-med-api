package med.voll.domain.consulta;

import med.voll.domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelamentoConsultas {

  @Autowired private ConsultaRepository consultaRepository;

  public void cancelar(DadosCancelamentoConsulta dados) {
    if (!consultaRepository.existsById(dados.idConsulta())) {
      throw new ValidacaoException("Id da consulta informado não existe!");
    }
    var consulta = consultaRepository.getReferenceById(dados.idConsulta());
    consulta.cancelar(dados.motivo());
  }
}
