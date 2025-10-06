package br.com.upe.academia.AcademiaWeb.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.MappedByteBuffer;
import java.util.List;
import java.util.UUID;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idGrupo;
    private String nomeGrupo;
    private String DescricaoGrupo;
//many to one
    @ManyToOne
    @JoinColumn(name = "id_personal")
    private Personal personal;

//// many to many
    @ManyToMany
    @JoinTable(
            name = "Lista_Alunos",
            joinColumns = @JoinColumn(name = "id_grupo"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private List<Aluno> ListaAlunos;
}
