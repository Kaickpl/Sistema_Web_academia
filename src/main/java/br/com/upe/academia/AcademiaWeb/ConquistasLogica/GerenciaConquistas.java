package br.com.upe.academia.AcademiaWeb.ConquistasLogica;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas.*;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SessaoProgressaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Services.MedidasCorporaisService;
import br.com.upe.academia.AcademiaWeb.Services.SerieSessaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GerenciaConquistas {
    private final Contexto contextoConquistas;


    public void decisaoConquista(SessaoProgressaoResponseDTO sessaoProgressaoResponseDTO){
        UUID idAluno = sessaoProgressaoResponseDTO.getAlunoId();
        double peso = sessaoProgressaoResponseDTO.getPeso();
        String exercicio = sessaoProgressaoResponseDTO.getNomeExercicio();
        int repeticoes = sessaoProgressaoResponseDTO.getNumeroDeRepeticoes();
        double volumeSerie = peso * repeticoes;

        if (peso >= 200 && exercicio.equals("Legpress")){
            ConquistasInterface conquistaLegpress = new Levantou200QuilosLegpress();
            contextoConquistas.setTipo(conquistaLegpress);
            contextoConquistas.registrar(idAluno);
        } else if (peso >= 50 && exercicio.equals("Supino inclinado")) {
            ConquistasInterface conquistaSupinoInclinado = new Levantou50QuilosSupinoInclinado();
            contextoConquistas.setTipo(conquistaSupinoInclinado);
            contextoConquistas.registrar(idAluno);
        } else if (repeticoes >= 20) {
            ConquistasInterface conquistaResistencia = new Resistencia();
            contextoConquistas.setTipo(conquistaResistencia);
            contextoConquistas.registrar(idAluno);
        } else if (volumeSerie > 500) {
            ConquistasInterface conquistaVolume500 = new Volume500();
            contextoConquistas.setTipo(conquistaVolume500);
            contextoConquistas.registrar(idAluno);
        }
    }

    public void decisaoConquistaObjetivo(UUID idUsuario) {
        ConquistasInterface conquistaObjetivo = new AtingiuObjetivo();
        contextoConquistas.setTipo(conquistaObjetivo);
        contextoConquistas.registrarConquistaObjetivo(idUsuario);
    }
}
