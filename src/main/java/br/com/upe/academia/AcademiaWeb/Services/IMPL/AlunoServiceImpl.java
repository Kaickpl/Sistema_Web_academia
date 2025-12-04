package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoResponseDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TrocaSenhaDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Exceptions.*;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    private TreinoService treinoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean existeEmail(String email) {

        return alunoRepository.findByEmail(email).isPresent();
    }


    public Aluno cadastrarAluno(AlunoDTOs alunoDTOs) {
        alunoDTOs.setTipo(Tipo.aluno);

        if (alunoRepository.findByEmail(alunoDTOs.getEmail()).isPresent()) {
            throw new UsuarioExistenteException("Usuário já cadastrado com este email: "+ alunoDTOs.getEmail());
        }
        if(alunoDTOs.getEmail() == null || alunoDTOs.getEmail().isBlank()) {
            throw new CampoObrigatorioException("O campo de e-mail é obrigatório.");
        }

        if (alunoDTOs.getNomeUsuario() == null || alunoDTOs.getNomeUsuario().isBlank()) {
            throw new CampoObrigatorioException("O campo de nome de usuário é obrigatório.");
        }
        if (alunoDTOs.getSenha() == null || alunoDTOs.getSenha().isBlank()) {
            throw new CampoObrigatorioException("O campo de senha é obrigatório.");
        }
        if (!this.validarEmail(alunoDTOs.getEmail())) {
            throw new EmailInvalidoException("Formato de e-mail inválido. Informe um e-mail no formato nome@dominio.com.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNasc = LocalDate.parse(alunoDTOs.getDataNascimento(), formatter);

        // Verificar se está no futuro
        if (dataNasc.isAfter(LocalDate.now())) {
            throw new OperacaoNaoPermitidaException("A data de nascimento não pode ser no futuro.");
        }


        Aluno aluno =  new Aluno();
        aluno.setEmail(alunoDTOs.getEmail());
        String password = passwordEncoder.encode(alunoDTOs.getSenha());
        aluno.setSenha(password);
        aluno.setDataNascimento(alunoDTOs.getDataNascimento());
        aluno.setTelefone(alunoDTOs.getTelefone());
        aluno.setTipo(alunoDTOs.getTipo());
        aluno.setNomeUsuario(alunoDTOs.getNomeUsuario());
        aluno.setSaldoMoedas(alunoDTOs.getSaldoMoedas());
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno alterarAluno(UUID id, AlunoDTOs alunoDTOs) {
        Optional<Aluno> idExiste = alunoRepository.findById(id);
        if (idExiste.isEmpty()) {
            throw new InformacaoNaoEncontradoException("Usuario com ID: "+ id + " não encontrado. Tente novamente.");
        }
        Aluno alunoEncontrado = idExiste.get();
        if (alunoDTOs.getEmail() != null && !alunoDTOs.getEmail().equals(alunoEncontrado.getEmail())) {

            if (alunoRepository.findByEmail(alunoDTOs.getEmail()).isPresent()) {
                throw new UsuarioExistenteException("Usuário já cadastrado com esse email: "+ alunoDTOs.getEmail());
            }
            if (validarEmail(alunoDTOs.getEmail()) == false) {
                return null;
            }
            alunoEncontrado.setEmail(alunoDTOs.getEmail());
        }
        if (alunoDTOs.getNomeUsuario() != null) {
            if (alunoDTOs.getNomeUsuario().isBlank()) {
                throw new ValorNuloNaoPermitidoException("O campo 'nome de usuário' não pode ser vazio ou nulo.");
            }
            alunoEncontrado.setNomeUsuario(alunoDTOs.getNomeUsuario());
        }
        if (alunoDTOs.getTelefone() != null) {
            alunoEncontrado.setTelefone(alunoDTOs.getTelefone());
        }
        if (alunoDTOs.getDataNascimento() != null) {
            if (alunoEncontrado.getDataNascimento()!= null){
                throw new OperacaoNaoPermitidaException("A data de nascimento já foi definida e não pode ser modificada.");
            }
             alunoEncontrado.setDataNascimento(alunoDTOs.getDataNascimento());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNasc = LocalDate.parse(alunoDTOs.getDataNascimento(), formatter);
            if (dataNasc.isAfter(LocalDate.now())) {
                throw new OperacaoNaoPermitidaException("A data de nascimento não pode ser no futuro.");
            }
        }
        if (alunoDTOs.getEmail() == null && alunoDTOs.getNomeUsuario() == null && alunoDTOs.getTelefone() == null) {
            throw new OperacaoNaoPermitidaException("Nenhuma informação foi enviada para atualização.");
        }

        return alunoRepository.save(alunoEncontrado);
    }

    @Override
    public Aluno trocarSenha( TrocaSenhaDTOs senhaDTOs){
        Optional<Aluno> alunoExiste = alunoRepository.findByEmail(senhaDTOs.getEmail());
        if (alunoExiste.isEmpty()) {
            throw new InformacaoNaoEncontradoException("Nenhum aluno com esse email: "+ senhaDTOs.getEmail());
        }

        if (senhaDTOs.getNovaSenha() == null || senhaDTOs.getNovaSenha().isBlank() ||
            senhaDTOs.getConfirmaSenha() == null || senhaDTOs.getConfirmaSenha().isBlank()) {
            throw new CampoObrigatorioException("Os campos de senha e confirmação de senha são obrigatórios.");

        }if(!senhaDTOs.getNovaSenha().equals(senhaDTOs.getConfirmaSenha())){
            throw new OperacaoNaoPermitidaException("As senhas informadas não conferem.");
        }

        if (senhaDTOs.getNovaSenha().equals(alunoExiste.get().getSenha())) {
            throw new OperacaoNaoPermitidaException("A nova senha não pode ser igual a atual.");
        }
        Aluno alunoEncontrado = alunoExiste.get();

        alunoEncontrado.setSenha(passwordEncoder.encode(senhaDTOs.getNovaSenha()));
        return alunoRepository.save(alunoEncontrado);
    }

    @Override
    public Boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    @Override
    public List<Treino> listarTreinos(UUID idAluno) {
        Aluno aluno  = this.buscarAlunoPorId(idAluno);
        return aluno.getTreinosAtribuidos();
    }

    @Override
    public Treino atribuirTreinoAluno(UUID idAluno, UUID idTreino, boolean isCopiaCompartilhada) {
        Aluno aluno = this.buscarAlunoPorId(idAluno);
        Treino treinoOriginal = treinoService.buscarTreino(idTreino);
        Treino treinoParaAtribuir = treinoOriginal;

        if (isCopiaCompartilhada) {
            treinoParaAtribuir = treinoService.deepCopyTreino(treinoOriginal);
        }

        List<Treino> treinosAtuais = aluno.getTreinosAtribuidos();

        if (!treinosAtuais.contains(treinoParaAtribuir)) {
            treinosAtuais.add(treinoParaAtribuir);
        }
        alunoRepository.save(aluno);

        return treinoParaAtribuir;
    }

    @Override
    public void removerTreinoAluno(UUID idAluno, UUID idTreino) {
        Aluno aluno = this.buscarAlunoPorId(idAluno);
        Treino treino = treinoService.buscarTreino(idTreino);

        if(aluno.getTreinosAtribuidos().contains(treino)) {
            aluno.getTreinosAtribuidos().remove(treino);
        } else {
            throw new RuntimeException("O treino não está atribuído a este aluno.");
        }
        alunoRepository.save(aluno);
    }

    @Override
    @Transactional
    public List<UUID> buscarIdAlunoPorTreino(UUID idTreino) {
        List<Aluno> alunos = alunoRepository.findByTreinosAtribuidos_IdTreino(idTreino);
        return alunos.stream().map(Aluno::getIdUsuario).collect(Collectors.toList());
    }

    @Override
    public Treino buscarTreinoUnico(UUID idAluno ,UUID idTreino) {
        Aluno  aluno = this.buscarAlunoPorId(idAluno);
        treinoService.buscarTreino(idTreino);
        Treino treinoEncontrado = aluno.getTreinosAtribuidos().stream().filter(t -> t.getIdTreino().equals(idTreino)).findFirst().get();
        return treinoEncontrado;
    }

    @Override
    public List<Grupo> ListarGruposAluno(UUID idAluno) {
        Optional<Aluno> aluno = alunoRepository.findById(idAluno);
        if (aluno.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Nenhum aluno cadastrado com esse ID: " + idAluno);
        }
        List<Grupo> grupos = aluno.get().getGrupos();
        if (grupos == null || grupos.isEmpty()) {
            throw new OperacaoNaoPermitidaException("Aluno não está em nenhum grupo");
        }
        return grupos;
    }

    @Override
    public void removerAluno(UUID id) {
        if (!alunoRepository.existsById(id)) {
            throw new InformacaoNaoEncontradoException("Nenhum aluno cadastrado com esse ID: " + id);
        }
        alunoRepository.deleteById(id);
    }

    @Override
    public List<Aluno> buscarAlunoPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new CampoObrigatorioException("O campo de nome para busca é obrigatório.");
        }
        List<Aluno> alunos = alunoRepository.findByNomeUsuarioContaining(nome);
        if (alunos.isEmpty()){
            throw new InformacaoNaoEncontradoException("Nenhum aluno com esse nome: " + nome);
        }
        return alunos;
    }

    @Override
    public Aluno buscarAlunoPorId(UUID idAluno) {
        return alunoRepository.findById(idAluno).orElseThrow(() -> new InformacaoNaoEncontradoException("Aluno não encontrado com ID " + idAluno));
    }

    @Override
    public Page<Aluno> listarAlunos(Pageable page) {
        Page<Aluno> alunos = alunoRepository.findAll(page);
        if  (alunos.isEmpty()) {
            throw new InformacaoNaoEncontradoException("Nenhum aluno cadastrado foi encontrado.");
        }
        return alunoRepository.findAll(page);
    }

    @Override
    public Aluno buscarAlunoPorEmail(String email) {
        return alunoRepository.findByEmail(email).orElse(null);
    }

    @Override
    public AlunoResponseDTOs VerPerfil(UUID idAluno) {
        Optional<Aluno> aluno = alunoRepository.findById(idAluno);
        if (aluno.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuario com esse ID: " + idAluno + "não encontrado!");
        }
        return new AlunoResponseDTOs(
                aluno.get()
        );
    }

}

