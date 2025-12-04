package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.*;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.CommandHistory;
import br.com.upe.academia.AcademiaWeb.Services.SerieSessaoService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import br.com.upe.academia.AcademiaWeb.utils.TreinoMapper;
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
    @Autowired
    private TreinoService treinoService;

    @PostMapping
    // AlunoResponse no post
    public ResponseEntity<AlunoResponseDTOs> cadastrarAluno(@RequestBody AlunoDTOs alunoDTOs) {
        Aluno aluno = alunoService.cadastrarAluno(alunoDTOs);
        if (aluno == null) {
            return ResponseEntity.badRequest().build();
        }
        AlunoResponseDTOs dto = new AlunoResponseDTOs(aluno);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable UUID id) {
        alunoService.removerAluno(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AlunoResponseDTOs>> buscaraluno(@RequestParam String nome) {
        List<AlunoResponseDTOs> alunos = alunoService.buscarAlunoPorNome(nome).stream().map(AlunoResponseDTOs::new).collect(Collectors.toList());
        return ResponseEntity.status(200).body(alunos);    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<Page<AlunoResponseDTOs>> listar(@PageableDefault(size = 2)Pageable page) {
        return ResponseEntity.ok(alunoService.listarAlunos(page).map(AlunoResponseDTOs::new));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTOs> atualizarAluno(@RequestBody AlunoDTOs alunoDTOs, @PathVariable UUID id) {
        Aluno alunoExistente = alunoService.alterarAluno(id,alunoDTOs);
        return ResponseEntity.ok(new AlunoResponseDTOs(alunoExistente));
    }

    @PutMapping("/RecuperarSeha")
    public ResponseEntity<AlunoResponseDTOs> recuperarSenha(@RequestBody TrocaSenhaDTOs senhaDTOs) {
            Aluno alunoExixste = alunoService.trocarSenha(senhaDTOs);
            return ResponseEntity.ok(new AlunoResponseDTOs(alunoExixste));
    }

    @PostMapping("/{idAluno}/treinos")
    public ResponseEntity<AlunoTreinoDTO> adicionarTreino(@PathVariable UUID idAluno, @RequestBody AlunoTreinoDTO alunoTreinoDTO) {
        Treino treinoAtribuido = alunoService.atribuirTreinoAluno(
                idAluno,
                alunoTreinoDTO.getIdTreino(),
                alunoTreinoDTO.isCopiaCompartilhada());

        alunoTreinoDTO.setIdAluno(idAluno);
        alunoTreinoDTO.setIdTreino(treinoAtribuido.getIdTreino());
        return ResponseEntity.ok(alunoTreinoDTO);
    }

    @GetMapping("/{idAluno}")
    public ResponseEntity<AlunoTreinosResponseDTO> buscarTreinosALuno(@PathVariable UUID idAluno) {
        AlunoTreinosResponseDTO alunoTreinosResponseDTO = new AlunoTreinosResponseDTO();
        List <TreinoDTO> treinos = alunoService.listarTreinos(idAluno).stream().map(TreinoDTO::new).collect(Collectors.toList());
        alunoTreinosResponseDTO.setIdAluno(idAluno);
        alunoTreinosResponseDTO.setNomeAluno(alunoService.buscarAlunoPorId(idAluno).getNomeUsuario());
        alunoTreinosResponseDTO.setTreinos(treinos);
        return ResponseEntity.ok(alunoTreinosResponseDTO);
        }

    @GetMapping("/{idAluno}/treinos/{idTreino}")
    public ResponseEntity<TreinoCompletoResponseDTO> buscarTreinoEspecifico(@PathVariable UUID idAluno, @PathVariable UUID idTreino){
        Treino treino = alunoService.buscarTreinoUnico(idAluno, idTreino);
        TreinoCompletoResponseDTO treinoCompletoResponseDTO = treinoMapper.toTreinoCompletoResponseDTO(treino);
        return ResponseEntity.status(200).body(treinoCompletoResponseDTO);
    }

    @DeleteMapping("/{idAluno}/treinos/{idTreino}")
    public ResponseEntity<Void> removerTreinoAluno(@PathVariable UUID idAluno, @PathVariable UUID idTreino){
        this.buscarTreinoEspecifico(idAluno, idTreino);
        alunoService.removerTreinoAluno(idAluno, idTreino);
        treinoService.deletarTreino(idTreino);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("ListarGruposAluno/{idAluno}")
    public ResponseEntity<List<GruposDoAlunoDTOs>>ListaGruposAluno(@PathVariable UUID idAluno){
        List<Grupo> grupo = alunoService.ListarGruposAluno(idAluno);
        List<GruposDoAlunoDTOs> gda = grupo.stream().map(GruposDoAlunoDTOs::new)
                .toList();
        return ResponseEntity.ok(gda);
    }

    @GetMapping("/VerPerfil/{idAluno}")
    public ResponseEntity<AlunoResponseDTOs> verPerfil(@PathVariable UUID idAluno) {
        AlunoResponseDTOs alunoResponseDTOs = alunoService.VerPerfil(idAluno);
        return ResponseEntity.ok(alunoResponseDTOs);
    }
}
