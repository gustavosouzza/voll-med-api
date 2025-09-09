package med.voll.paciente;

import aj.org.objectweb.asm.commons.Remapper;
import med.voll.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long > {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
}
