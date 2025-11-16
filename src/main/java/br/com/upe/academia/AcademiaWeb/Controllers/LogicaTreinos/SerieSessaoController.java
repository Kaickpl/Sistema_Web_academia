package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.CommandHistory;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelCriarSerieSessao;
import br.com.upe.academia.AcademiaWeb.Services.SerieSessaoService;
import br.com.upe.academia.AcademiaWeb.utils.SerieSessaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos/{idAluno}/sessoes/{idTreinoSessao}/{idExercicioSessao}")
public class SerieSessaoController {

    @Autowired
    SerieSessaoService serieSessaoService;

    @Autowired
    CommandHistory commandHistory;

    @Autowired
    SerieSessaoMapper serieSessaoMapper;

    @PostMapping
    public ResponseEntity<SerieSessaoDTO> registrarSerieSessao(@RequestBody SerieSessaoDTO serieSessaoDTO){
        ExecutavelCriarSerieSessao comandoCriarSerieSessao = new ExecutavelCriarSerieSessao(this.serieSessaoService, serieSessaoDTO);
        commandHistory.execute(comandoCriarSerieSessao);
        SerieSessaoDTO serieSessaoDTOSalva = comandoCriarSerieSessao.getSerieSessaoDTO();
        return ResponseEntity.status(HttpStatus.CREATED).body(serieSessaoDTOSalva);
    }

}
