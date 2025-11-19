package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.ConquistasLogica.GerenciaConquistas;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoDTO;
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
@RequestMapping("/exercicios/{idExercicioSessao}/series")

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


}
