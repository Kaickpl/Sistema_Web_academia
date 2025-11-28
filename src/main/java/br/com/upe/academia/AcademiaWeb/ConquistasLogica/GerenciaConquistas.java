package br.com.upe.academia.AcademiaWeb.ConquistasLogica;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas.*;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SessaoProgressaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Services.ConquistasService;
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
    private final ConquistasService conquistasService;


    public void decisaoConquista(SessaoProgressaoResponseDTO sessaoProgressaoResponseDTO, double pesoAluno){
        UUID idAluno = sessaoProgressaoResponseDTO.getAlunoId();
        double peso = sessaoProgressaoResponseDTO.getPeso();
        String exercicio = sessaoProgressaoResponseDTO.getNomeExercicio();
        int repeticoes = sessaoProgressaoResponseDTO.getNumeroDeRepeticoes();
        double volumeSerie = peso * repeticoes;

        if (peso >= 200 && exercicio.equals("Legpress")){
            ConquistasInterface conquistaLegpress = new Levantou200QuilosLegpress();
            contextoConquistas.setTipo(conquistaLegpress);
            contextoConquistas.registrar(idAluno);
        }

        if (peso >= 50 && exercicio.equals("Supino inclinado")) {
            ConquistasInterface conquistaSupinoInclinado = new Levantou50QuilosSupinoInclinado();
            contextoConquistas.setTipo(conquistaSupinoInclinado);
            contextoConquistas.registrar(idAluno);
        }
        if (repeticoes >= 20) {
            ConquistasInterface conquistaResistencia = new Resistencia();
            contextoConquistas.setTipo(conquistaResistencia);
            contextoConquistas.registrar(idAluno);
        }
        if (volumeSerie > 500) {
            ConquistasInterface conquistaVolume500 = new Volume500();
            contextoConquistas.setTipo(conquistaVolume500);
            contextoConquistas.registrar(idAluno);
        }
        if (peso > pesoAluno*3){
            ConquistasInterface formiguinha = new Formiguinha();
            contextoConquistas.setTipo(formiguinha);
            contextoConquistas.registrar(idAluno);
        }
        if (peso == 40) {
            ConquistasInterface quarentao = new Atingiu40quilos();
            contextoConquistas.setTipo(quarentao);
            contextoConquistas.registrar(idAluno);
        }
    }

    public void decisaoConquistaObjetivo(UUID idUsuario, String tipoMedida) {
        ConquistasInterface conquistaObjetivo = new AtingiuObjetivo(idUsuario, tipoMedida);

        contextoConquistas.setTipo(conquistaObjetivo);
        contextoConquistas.registrarConquistaObjetivo(idUsuario);
    }
}
