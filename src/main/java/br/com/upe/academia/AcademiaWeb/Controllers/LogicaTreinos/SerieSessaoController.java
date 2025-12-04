package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.ConquistasLogica.GerenciaConquistas;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioSessaoService;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.CommandHistory;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelCriarSerieSessao;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelDeletarSerieSessao;
import br.com.upe.academia.AcademiaWeb.Services.SerieSessaoService;
import br.com.upe.academia.AcademiaWeb.utils.SerieSessaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/exercicios/{idExercicioSessao}/series")

public class SerieSessaoController {

    @Autowired
    SerieSessaoService serieSessaoService;

    @Autowired
    CommandHistory commandHistory;

    @Autowired
    GerenciaConquistas gerenciaConquistas;

    @Autowired
    SerieSessaoMapper serieSessaoMapper;

    @PostMapping
    public ResponseEntity<SerieSessaoDTO> registrarSerieSessao(@PathVariable UUID idExercicioSessao, @RequestBody SerieSessaoDTO serieSessaoDTO){
        serieSessaoDTO.setIdExercicioSessao(idExercicioSessao);
        ExecutavelCriarSerieSessao comandoCriarSerieSessao = new ExecutavelCriarSerieSessao(this.serieSessaoService, serieSessaoDTO);
        commandHistory.execute(comandoCriarSerieSessao);
        SerieSessaoDTO serieSessaoDTOSalva = serieSessaoMapper.toDTO(comandoCriarSerieSessao.getSerieSessaoCriada());
        return ResponseEntity.status(HttpStatus.CREATED).body(serieSessaoDTOSalva);
    }

    @DeleteMapping("/{idSerieSessao}")
    public ResponseEntity<Void> apagarSerieSessao(@PathVariable UUID idSerieSessao){
        SerieSessaoDTO serieSessaoDTO = serieSessaoMapper.toDTO(serieSessaoService.buscarSerieSessao(idSerieSessao));
        ExecutavelDeletarSerieSessao comandoDeletarSerieSessao = new ExecutavelDeletarSerieSessao(this.serieSessaoService, this.serieSessaoMapper, serieSessaoDTO);
        commandHistory.execute(comandoDeletarSerieSessao);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idSerieSessao}")
    public ResponseEntity<SerieSessaoResponseDTO>  buscarSerieSessao(@PathVariable UUID idExercicioSessao,@PathVariable UUID idSerieSessao){
        SerieSessao serie =  serieSessaoService.buscarSerieSessaoSegura(idExercicioSessao ,idSerieSessao);
        SerieSessaoResponseDTO dto = serieSessaoMapper.toRespondeDTO(serie);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{idSerieSessao}")
    public ResponseEntity<SerieSessaoResponseDTO> editarSerieSessao(@PathVariable UUID idSerieSessao, @RequestBody SerieSessaoDTO serieSessaoDTO){
        SerieSessao serieSessaoAntiga = serieSessaoService.buscarSerieSessao(idSerieSessao);
        serieSessaoAntiga.setPeso(serieSessaoDTO.getPeso());
        serieSessaoAntiga.setNumeroDeRepeticoes(serieSessaoDTO.getNumeroDeRepeticoes());
        SerieSessaoDTO dto = serieSessaoMapper.toDTO(serieSessaoAntiga);
        SerieSessaoResponseDTO responseDTO = serieSessaoMapper.toRespondeDTO(serieSessaoAntiga);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{idAluno}/recordes/{idExercicio}")
    public ResponseEntity<SerieSessaoResponseDTO> buscarRecordPessoal(@PathVariable UUID idAluno, @PathVariable UUID idExercicio){
        SerieSessaoResponseDTO recorde = serieSessaoService.buscarRecordPorExercicio(idExercicio,idAluno);
        if(recorde==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recorde);
    }
}
