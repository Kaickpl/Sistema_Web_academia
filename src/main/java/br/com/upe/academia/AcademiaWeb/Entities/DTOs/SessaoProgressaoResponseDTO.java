package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Services.SerieSessaoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SessaoProgressaoResponseDTO {
    private double peso;
    private Integer numeroDeRepeticoes;
    private String nomeExercicio;
    private UUID alunoId;
    private Exercicio exercicioTemplate;
}
