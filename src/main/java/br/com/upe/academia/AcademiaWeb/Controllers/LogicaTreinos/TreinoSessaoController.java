package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoSessaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoSessaoResponseGetDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoSessao;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.CommandHistory;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelCriarTreinoSessao;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelDeletarTreinoSessao;
import br.com.upe.academia.AcademiaWeb.Services.TreinoSessaoService;
import br.com.upe.academia.AcademiaWeb.utils.TreinoSessaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/alunos/{idAluno}/sessoes")
public class TreinoSessaoController {
    @Autowired
    private CommandHistory commandHistory;

    @Autowired
    private TreinoSessaoService treinoSessaoService;

    @Autowired
    private TreinoSessaoMapper treinoSessaoMapper;

    @PostMapping
    public ResponseEntity<TreinoSessaoDTO> registrarSessaoTreino (@PathVariable UUID idAluno, @RequestBody @Validated TreinoSessaoDTO  treinoSessaoDTO){
        treinoSessaoDTO.setIdAluno(idAluno);
        ExecutavelCriarTreinoSessao comandoCriarTreinoSessao = new ExecutavelCriarTreinoSessao(this.treinoSessaoService, treinoSessaoDTO);
        commandHistory.execute(comandoCriarTreinoSessao);
        treinoSessaoDTO.setIdTreinoSessao(comandoCriarTreinoSessao.getTreinoSessaoCriadaId());
        TreinoSessaoDTO treinoSessaoCriada = treinoSessaoDTO;
        return ResponseEntity.status(HttpStatus.CREATED).body(treinoSessaoCriada);
    }

    @PutMapping("/{idTreinoSessao}/fechar")
    public ResponseEntity<TreinoSessaoResponseDTO> fecharSessaoTreino (@PathVariable UUID idAluno, @PathVariable UUID idTreinoSessao){
        TreinoSessao treinoEmExecucao = treinoSessaoService.fecharTreinoSessao(idTreinoSessao);
        TreinoSessaoResponseDTO dto = treinoSessaoMapper.toResponseDTO(treinoEmExecucao);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{idTreinoSessao}")
    public ResponseEntity<Void> deletarTreinoSessao(@PathVariable UUID idAluno, @PathVariable UUID idTreinoSessao){
        ExecutavelDeletarTreinoSessao comandoDeletarTreino = new ExecutavelDeletarTreinoSessao(this.treinoSessaoService, idTreinoSessao);
        commandHistory.execute(comandoDeletarTreino);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idTreinoSessao}")
    public ResponseEntity<TreinoSessaoResponseGetDTO> buscarTreinoSessao(@PathVariable UUID idAluno,@PathVariable UUID idTreinoSessao){
        TreinoSessao treinoSessao = treinoSessaoService.buscarSessaoPorId(idTreinoSessao);
        TreinoSessaoResponseGetDTO dtoResponse = treinoSessaoMapper.toResponseGetDTO(treinoSessao);
        return ResponseEntity.ok(dtoResponse);
    }

}
