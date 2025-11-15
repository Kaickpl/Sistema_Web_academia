package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
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
    private SerieSessao serieSessao;
    private double peso;
    private Integer numeroDeRepeticoes;
    private Exercicio exercicioTemplate;
    private double pesoTotal;
    @Autowired
    private SerieSessaoService serieSessaoService;

    public SessaoProgressaoResponseDTO(UUID idSerieSessao) {
        this.serieSessao = serieSessaoService.buscarSerieSessao(idSerieSessao);
        this.peso = serieSessao.getPeso();
        this.numeroDeRepeticoes = serieSessao.getNumeroDeRepeticoes();
        this.exercicioTemplate = serieSessao.getExercicioSessao().getExercicioTemplate();
        this.pesoTotal = serieSessao.getPesoTotal();
    }
}
