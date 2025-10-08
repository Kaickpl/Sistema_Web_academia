package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Progressao;
import br.com.upe.academia.AcademiaWeb.Services.ProgressaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/progressao")
public class ProgressaoController {
    @Autowired
    private ProgressaoService progressaoService;

    @GetMapping("/historico/{alunoId}")
    public List<Progressao> getHistorico(@PathVariable UUID alunoId, @RequestParam String exercicio) {
        return progressaoService.getHistoricoCarga(alunoId, exercicio);
    }
}
