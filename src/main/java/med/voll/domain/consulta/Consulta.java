package med.voll.domain.consulta;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import med.voll.domain.medico.Medico;
import med.voll.domain.paciente.Paciente;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "medico_id")
  private Medico medico;

  @ManyToOne
  @JoinColumn(name = "paciente_id")
  private Paciente paciente;

  private LocalDateTime data;

  @Column(name = "motivo_cancelamento")
  @Enumerated(EnumType.STRING)
  private MotivoCancelamento motivoCancelamento;

  public void cancelar(MotivoCancelamento motivo) {
    this.motivoCancelamento = motivo;
  }
}
