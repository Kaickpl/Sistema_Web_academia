package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Repositories.TreinoRepository;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class TreinoServiceImpl implements TreinoService {

    @Autowired
    TreinoRepository treinoRepository;

    // MUDAR O TRATAMENTO DE ERRO
    public Treino buscarTreino(UUID idTreino){
        Optional<Treino> treino = treinoRepository.findById(idTreino);
        return treino.orElseThrow(() -> new RuntimeException("Treino não encontrado"));
    }

    @Override
    @Transactional
    public Treino criarTreino(Treino treino){
        return this.treinoRepository.save(treino);
    }

    @Override
    @Transactional
    public Treino atualizarTreino(Treino treino){
        Treino treinoAtualizado = buscarTreino(treino.getIdTreino());
        treinoAtualizado.setNome(treino.getNome());
        treinoAtualizado.setDuracao(treino.getDuracao());
        return this.treinoRepository.save(treinoAtualizado);
    }

    @Override
    public void deletarTreino(UUID idTreino) {
        buscarTreino(idTreino);
        this.treinoRepository.deleteById(idTreino);
    }
    }

