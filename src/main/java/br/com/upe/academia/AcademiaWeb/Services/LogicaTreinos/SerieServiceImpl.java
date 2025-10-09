package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Repositories.SerieRepository;
import br.com.upe.academia.AcademiaWeb.Services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SerieServiceImpl implements SerieService {

    @Autowired
    SerieRepository serieRepository;

    @Override
    public Serie buscarSerie(UUID uuid){
        Optional<Serie> serie = serieRepository.findById(uuid);
        return serie.orElseThrow(() -> new RuntimeException("Serie não encontrada"));
    }

    @Transactional
    @Override
    public Serie adicionarSerie(Serie serie) {
        return this.serieRepository.save(serie);
    }

    @Transactional
    @Override
    public Serie atualizarSerie(Serie serie) {
        Serie serieAtt = buscarSerie(serie.getIdSerie());
        serieAtt.setPesoDaSerie(serie.getPesoDaSerie());
        serieAtt.setNumeroDeRepeticoes(serie.getNumeroDeRepeticoes());
        serieAtt.setConcluida(serie.isConcluida());
        return this.serieRepository.save(serieAtt);
    }

    @Override
    public void removerSerie(UUID uuid) {
        buscarSerie(uuid);
        try {
            this.serieRepository.deleteById(uuid);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir o serie, pois há entidades que dependem dela.");
        }
    }

    @Override
    public List<Serie> buscarSeriePorExercicio(Exercicio exercicio) {
        return this.serieRepository.findByExercicio(exercicio);
    }

}
