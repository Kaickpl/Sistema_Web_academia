package br.com.upe.academia.AcademiaWeb.Controllers;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.CommandHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private CommandHistory commandHistory;

    @PostMapping("/desfazer")
    public ResponseEntity<String> desfazerUltimaAcao(){
        commandHistory.undo();
        return ResponseEntity.ok("Ultima ação desfeita com sucesso");
    }

    @PostMapping("/refazer")
    public ResponseEntity<String> refazerUltimaAcao(){
        commandHistory.redo();
        return ResponseEntity.ok("Ultima ação refeita com sucesso");
    }

}
