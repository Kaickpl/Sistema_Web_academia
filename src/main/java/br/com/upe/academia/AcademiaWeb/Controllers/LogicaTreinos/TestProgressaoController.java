package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Progressao;
import br.com.upe.academia.AcademiaWeb.Services.ProgressaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/teste-progressao")
public class TestProgressaoController {
    @Autowired
    private ProgressaoService progressaoService;
    //precisa colocar isso no controller normal

    @PostMapping
    public Progressao testarSalvamento(@RequestBody TesteProgressaoDTO dto){
        return progressaoService.salvaCarga(UUID.fromString(dto.alunoId),
                dto.nomeExercicio,
                dto.peso);
    }

    static class TesteProgressaoDTO {
        public String alunoId;
        public String nomeExercicio;
        public int peso;
    }
}
