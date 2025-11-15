package br.com.upe.academia.AcademiaWeb.ConquistasLogica;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas.Levantou200QuilosLegpress;
import br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas.Levantou50QuilosSupinoInclinado;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SessaoProgressaoResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class GerenciaConquistas {
    private final Contexto contextoConquistas;
    public GerenciaConquistas(Contexto contexto){
        this.contextoConquistas = contexto;
    }
    public void decisaoConquista(SessaoProgressaoResponseDTO sessaoProgressaoResponseDTO){
        double peso = sessaoProgressaoResponseDTO.getPeso();
        String exercicio = sessaoProgressaoResponseDTO.getExercicioTemplate().getNomeExercicio();

        if (peso >= 200 && exercicio.equals("Legpress")){
            ConquistasInterface conquistaLegpress = new Levantou200QuilosLegpress();
            contextoConquistas.setTipo(conquistaLegpress);
            contextoConquistas.registrar(sessaoProgressaoResponseDTO.getAlunoId());
        } else if (peso >= 50 && exercicio.equals("Supino inclinado")) {
            ConquistasInterface conquistaSupinoInclinado = new Levantou50QuilosSupinoInclinado();
            contextoConquistas.setTipo(conquistaSupinoInclinado);
            contextoConquistas.registrar(sessaoProgressaoResponseDTO.getAlunoId());
        }
    }
}
