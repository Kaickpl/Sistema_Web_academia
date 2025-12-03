package br.com.upe.academia.AcademiaWeb.Services;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoSessao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TreinoSessaoService {
    public TreinoSessao buscarSessaoPorId(UUID idAluno, UUID idTreinoSessao);

    public TreinoSessao iniciarTreinoSessao(TreinoSessaoDTO treinoSessao);

    public TreinoSessao fecharTreinoSessao(UUID idAluno,UUID idTreinoSessao);

    public void apagarTreinoSessao(UUID idTreinoSessao);

    public TreinoSessao recriarTreinoSessao(TreinoSessao sessaoAntiga);

    public TreinoSessao adicionarComentario(UUID idTreinoSessao, UUID idAluno ,String comentario);

    public TreinoSessao salvarEntidade(TreinoSessao treinoSessao);

    public void restaurarExecucaoCompleta(TreinoSessao novaSessao, List<ExercicioSessao> exerciciosSalvos);

    public TreinoSessao buscarSessaoPorIdUnico(UUID idTreinoSessao);
}
