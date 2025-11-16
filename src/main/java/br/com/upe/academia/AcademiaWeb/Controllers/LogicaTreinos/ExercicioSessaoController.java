package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioSessaoService;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.CommandHistory;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelCriarExercicio;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelCriarExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelCriarSerieSessao;
import br.com.upe.academia.AcademiaWeb.utils.ExercicioSessaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos/{idAluno}/sessoes/{idTreino}")
public class ExercicioSessaoController {

    @Autowired
    private ExercicioSessaoService exercicioSessaoService;

    @Autowired
    private CommandHistory commandHistory;

    @Autowired
    private ExercicioSessaoMapper exercicioSessaoMapper;

    @PostMapping
    public ResponseEntity<ExercicioSessaoDTO> registrarSessaoExercicio(@RequestBody ExercicioSessaoDTO exercicioSessaoDTO){
        ExecutavelCriarExercicioSessao comandoCriarSerieSessao = new ExecutavelCriarExercicioSessao(this.exercicioSessaoService, exercicioSessaoDTO);
        commandHistory.execute(comandoCriarSerieSessao);
        ExercicioSessaoDTO exercicioSessaoDtoSalvo = comandoCriarSerieSessao.getExercicioSessaoDTO();
        return ResponseEntity.status(HttpStatus.CREATED).body(exercicioSessaoDtoSalvo);
    }


}
