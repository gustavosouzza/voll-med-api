package med.voll.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.domain.consulta.AgendaDeConsultas;
import med.voll.domain.consulta.DadosAgendamentoConsulta;
import med.voll.domain.consulta.DadosCancelamentoConsulta;
import med.voll.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;
    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        agenda.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null , null    ) );
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
