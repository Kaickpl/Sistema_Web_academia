package br.com.upe.academia.AcademiaWeb.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.MappedByteBuffer;
import java.util.ArrayList;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_id")
    private Personal personal;

    @ManyToMany
    @JoinTable(
            name = "grupo_aluno",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Aluno> alunos = new ArrayList<>();


}
