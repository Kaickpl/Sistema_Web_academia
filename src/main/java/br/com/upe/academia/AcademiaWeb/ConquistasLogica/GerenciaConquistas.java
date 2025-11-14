package br.com.upe.academia.AcademiaWeb.ConquistasLogica;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ProgressaoDTOs;
import org.springframework.stereotype.Service;

@Service
public class GerenciaConquistas {
    private final Contexto contextoConquistas;
    public GerenciaConquistas(Contexto contexto){
        this.contextoConquistas = contexto;
    }
    public void decisaoConquista(ProgressaoDTOs progressaoDTOs){
        int peso = progressaoDTOs.getPeso();
        String exercicio = progressaoDTOs.getNomeExercicio();

        if (peso >= 200 && exercicio.equals("Legpress")){
            ConquistasInterface conquistaLegpress = new Levantou200QuilosLegpress();
            contextoConquistas.setTipo(conquistaLegpress);
            contextoConquistas.registrar(progressaoDTOs.getAlunoId());
        }
    }
}
