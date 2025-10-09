package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;

import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.Executavel;
import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
public class CommandHistory {
    private Stack<Executavel> unDo = new Stack<>();
    private Stack<Executavel> reDo = new Stack<>();

    public void execute(Executavel executavel) {
        executavel.executar();
        this.unDo.push(executavel);
        reDo.clear();
    }

    public void undo() {
        if (!unDo.isEmpty()) {
            Executavel lastExecutavel = unDo.pop();
            lastExecutavel.desfazer();
            reDo.push(lastExecutavel);
        }
    }

    public void redo() {
        if (!reDo.isEmpty()) {
            Executavel lastExecutavel = reDo.pop();
            lastExecutavel.executar();
            reDo.push(lastExecutavel);
        }
    }


}
