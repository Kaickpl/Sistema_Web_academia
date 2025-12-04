package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import br.com.upe.academia.AcademiaWeb.Exceptions.InformacaoNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Repositories.SerieRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.TreinoExercicioRepository;
import br.com.upe.academia.AcademiaWeb.Services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SerieServiceImpl implements SerieService {

    @Autowired
    TreinoExercicioRepository treinoRepository;

    @Autowired
    SerieRepository serieRepository;

    @Override
    public Serie buscarSerie(UUID uuid){
        Optional<Serie> serie = serieRepository.findById(uuid);
        return serie.orElseThrow(() -> new InformacaoNaoEncontradoException("Serie não encontrada com o ID "+ uuid));
    }

    @Transactional
    @Override
    public Serie adicionarSerie(Serie serie) {
        return this.serieRepository.save(serie);
    }

    @Override
    public void removerSerie(UUID uuid) {
        buscarSerie(uuid);
        serieRepository.deleteById(uuid);
    }

    @Override
    public List<Serie> buscarSeriePorRegra(UUID idTreinoExercicio) {
        TreinoExercicio regra =  treinoRepository.findById(idTreinoExercicio).get();
        return this.serieRepository.findByTreinoExercicio(regra);
    }

}
