package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.*;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Repositories.TreinoRepository;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.CommandHistory;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelAtribuirTreinoAluno;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelRemoverTreinoAluno;
import br.com.upe.academia.AcademiaWeb.utils.TreinoMapper;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/api/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CommandHistory commandHistory;
    @Autowired
    private TreinoMapper treinoMapper;

    @PostMapping



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable UUID id) {
        alunoService.removerAluno(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AlunoResponseDTOs>> buscaraluno(@RequestParam String nome) {
        List<AlunoResponseDTOs> alunos = alunoService.buscarAlunoPorNome(nome).stream().map(AlunoResponseDTOs::new).collect(Collectors.toList());
        if (alunos != null) {
            return ResponseEntity.status(200).body(alunos);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<Page<AlunoResponseDTOs>> listar(@PageableDefault(size = 2)Pageable page) {
        return ResponseEntity.ok(alunoService.listarAlunos(page).map(AlunoResponseDTOs::new));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTOs> atualizarAluno(@RequestBody AlunoDTOs alunoDTOs, @PathVariable UUID id) {

        Aluno alunoExistente = alunoService.alterarAluno(id,alunoDTOs);
        if (alunoExistente == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new AlunoResponseDTOs(alunoExistente));
    }

    @PutMapping("/RecuperarSeha/{Email}")
    public ResponseEntity<AlunoResponseDTOs> recuperarSenha(@PathVariable String Email, @RequestBody TrocaSenhaDTOs senhaDTOs) {
        Aluno alunoExixste = alunoService.trocarSenha(Email, senhaDTOs);
        if (alunoExixste == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new AlunoResponseDTOs(alunoExixste));
    }

    @PostMapping("/treinos")
    public ResponseEntity<AlunoTreinoDTO> adicionarTreino(@RequestBody AlunoTreinoDTO alunoTreinoDTO) {
        ExecutavelAtribuirTreinoAluno atribuirTreinoAluno = new ExecutavelAtribuirTreinoAluno(alunoService, alunoTreinoDTO.getIdAluno(), alunoTreinoDTO.getIdTreino());
        commandHistory.execute(atribuirTreinoAluno);
        return ResponseEntity.ok(alunoTreinoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoTreinosResponseDTO> buscarTreinosALuno(@PathVariable UUID id) {
        AlunoTreinosResponseDTO alunoTreinosResponseDTO = new AlunoTreinosResponseDTO();
        List <TreinoDTO> treinos = alunoService.listarTreinos(id).stream().map(TreinoDTO::new).collect(Collectors.toList());
        alunoTreinosResponseDTO.setIdAluno(id);
        alunoTreinosResponseDTO.setNomeAluno(alunoService.buscarAlunoPorId(id).getNomeUsuario());
        alunoTreinosResponseDTO.setTreinos(treinos);
        return ResponseEntity.status(200).body(alunoTreinosResponseDTO);
        }

    @GetMapping("/{idAluno}/treinos/{idTreino}")
    public ResponseEntity<TreinoDTO> buscarTreinoEspecifico(@PathVariable UUID idAluno, @PathVariable UUID idTreino){
        Treino treino = alunoService.buscarTreinoUnico(idAluno, idTreino);
        TreinoDTO treinoDTO = treinoMapper.toDTO(treino);
        return ResponseEntity.status(200).body(treinoDTO);
    }

    @DeleteMapping("/{idAluno}/treinos/{idTreino}")
    public ResponseEntity<Void> removerTreinoAluno(@PathVariable UUID idAluno, @PathVariable UUID idTreino){
        ExecutavelRemoverTreinoAluno executavelRemoverTreinoAluno = new ExecutavelRemoverTreinoAluno(idAluno, idTreino, alunoService);
        commandHistory.execute(executavelRemoverTreinoAluno);
        return ResponseEntity.status(200).build();
    }
    @GetMapping("ListarGruposAluno/{idAluno}")
    public ResponseEntity<List<GruposDoAlunoDTOs>>ListaGruposAluno(@PathVariable UUID idAluno){
        List<Grupo> grupo = alunoService.ListarGruposAluno(idAluno);
        if (grupo.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        List<GruposDoAlunoDTOs> gda = grupo.stream().map(GruposDoAlunoDTOs::new)
                .toList();
        return ResponseEntity.ok(gda);
    }
    }
