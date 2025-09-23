package med.voll.domain.consulta;

import jakarta.persistence.*;
import lombok.*;
import med.voll.domain.medico.Medico;
import med.voll.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Table (name = "consultas")
@Entity (name = "Consulta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue (strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "id_medico")
    private Medico medico;

    @ManyToOne
    @JoinColumn (name = "id_paciente")
    private Paciente paciente;

    private LocalDateTime data;

}
