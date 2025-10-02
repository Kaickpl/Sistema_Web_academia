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

    @OneToOne
    @JoinColumn(name = "id_Personal" )
    private Personal personal;

    @OneToMany (mappedBy = "grupo")
    private List<Aluno> alunos;
}
