package med.voll.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.domain.endereco.Endereco;

@Entity
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String email;
  private String telefone;
  private String cpf;

  @Embedded private Endereco endereco;
  private boolean ativo;

  public Paciente(DadosCadastroPaciente dados) {
    this.ativo = true;
    this.nome = dados.nome();
    this.email = dados.email();
    this.telefone = dados.telefone();
    this.cpf = dados.cpf();
    this.endereco = new Endereco(dados.endereco());
  }

  public void atualizarInformacoes(@Valid DadosAtualizacaoPaciente dados) {
    this.nome = dados.nome() != null ? dados.nome() : this.nome;
    this.telefone = dados.telefone() != null ? dados.telefone() : this.telefone;
    if (dados.endereco() != null) {
      this.endereco.atualizarInformacoes(dados.endereco());
    }
  }

  public void excluir() {
    this.ativo = false;
  }
}
