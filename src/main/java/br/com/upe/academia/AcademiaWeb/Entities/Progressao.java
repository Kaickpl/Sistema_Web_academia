package br.com.upe.academia.AcademiaWeb.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Progressao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idProgressao;
    /*
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idExercicio")
    private Exercicio exercicio;

    @ManyToOne
    @JoinColumn(name = "idConquista")
    private Conquista conquista;
     */
}
