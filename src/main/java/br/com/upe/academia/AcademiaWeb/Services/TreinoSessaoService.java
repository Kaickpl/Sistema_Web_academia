package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoSessao;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface TreinoSessaoService {
    public TreinoSessao buscarSessaoPorId(UUID idTreinoSessao);

    public TreinoSessao iniciarTreinoSessao(TreinoSessaoDTO treinoSessao);

    public TreinoSessao fecharTreinoSessao(UUID idTreinoSessao);

    public void apagarTreinoSessao(UUID idTreinoSessao);

    public TreinoSessao recriarTreinoSessao(TreinoSessao sessaoAntiga);

    public void adicionarComentario(UUID idTreinoSessao, String comentario);
}
