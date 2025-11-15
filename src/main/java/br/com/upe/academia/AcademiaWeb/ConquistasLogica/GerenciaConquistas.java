package br.com.upe.academia.AcademiaWeb.ConquistasLogica;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas.Formiguinha;
import br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas.Levantou200QuilosLegpress;
import br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas.Levantou50QuilosSupinoInclinado;
import br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas.Resistencia;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SessaoProgressaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Services.MedidasCorporaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GerenciaConquistas {
    private final Contexto contextoConquistas;
    public GerenciaConquistas(Contexto contexto){
        this.contextoConquistas = contexto;
    }
    @Autowired
    MedidasCorporaisService medidasCorporaisService;

    public void decisaoConquista(SessaoProgressaoResponseDTO sessaoProgressaoResponseDTO){
        UUID idAluno = sessaoProgressaoResponseDTO.getAlunoId();
        double pesoAluno = medidasCorporaisService.mostrarMedidasAtuais(idAluno).getPeso();
        double peso = sessaoProgressaoResponseDTO.getPeso();
        String exercicio = sessaoProgressaoResponseDTO.getExercicioTemplate().getNomeExercicio();
        int repeticoes = sessaoProgressaoResponseDTO.getNumeroDeRepeticoes();

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
        } else if (peso >= (pesoAluno*3)) {
            ConquistasInterface conquistasFormiguinha = new Formiguinha();
            contextoConquistas.setTipo(conquistasFormiguinha);
            contextoConquistas.registrar(idAluno);
        }
    }
}
