package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoTreinoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TrocaSenhaDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private TreinoService treinoService;

    public boolean validaremail(String email) {

        return alunoRepository.findByEmail(email).isPresent();
    }


    public Aluno cadastrarAluno(AlunoDTOs alunoDTOs) {
        alunoDTOs.setTipo(Tipo.aluno);

        if (alunoRepository.findByEmail(alunoDTOs.getEmail()).isPresent()) {
            return null;
        }

        if (alunoDTOs.getNomeUsuario() == null || alunoDTOs.getNomeUsuario().isEmpty()) {
            return null;
        }
        if (alunoDTOs.getSenha() == null || alunoDTOs.getSenha().isEmpty()) {
            return null;
        }
        if (validarGmail(alunoDTOs.getEmail()) == false) {
            return null;

        }

        Aluno aluno =  new Aluno();
        aluno.setEmail(alunoDTOs.getEmail());
        aluno.setSenha(alunoDTOs.getSenha());
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
            return null;
        }
        Aluno alunoEncontrado = idExiste.get();
        if (alunoDTOs.getEmail() != null && !alunoDTOs.getEmail().equals(alunoEncontrado.getEmail())) {
            if (validaremail(alunoDTOs.getEmail())) {
                return null;
            }
            if (validarGmail(alunoDTOs.getEmail()) == false) {
                return null;
            }
            alunoEncontrado.setEmail(alunoDTOs.getEmail());
        }
        if (alunoDTOs.getNomeUsuario() != null) {
            alunoEncontrado.setNomeUsuario(alunoDTOs.getNomeUsuario());
        }
        if (alunoDTOs.getTelefone() != null) {
            alunoEncontrado.setTelefone(alunoDTOs.getTelefone());
        }
        return alunoRepository.save(alunoEncontrado);
    }

    @Override
    public Aluno trocarSenha(String Email, TrocaSenhaDTOs senhaDTOs){
        Optional<Aluno> alunoExiste = alunoRepository.findByEmail(Email);
        if (alunoExiste.isEmpty()) {
            return null;
        }

        if (senhaDTOs.getNovaSenha() == null || senhaDTOs.getNovaSenha().isBlank() ||
            senhaDTOs.getConfirmaSenha() == null || senhaDTOs.getConfirmaSenha().isBlank()) {
            return null;

        }if(!senhaDTOs.getNovaSenha().equals(senhaDTOs.getConfirmaSenha())){
            return null;
        }

        if (senhaDTOs.getNovaSenha().equals(alunoExiste.get().getSenha())) {
            return null;
        }
        Aluno alunoEncontrado = alunoExiste.get();
        alunoEncontrado.setSenha(senhaDTOs.getConfirmaSenha());
        return alunoRepository.save(alunoEncontrado);
    }

    @Override
    public Boolean validarGmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    @Override
    public List<Treino> listarTreinos(UUID idAluno) {
        Aluno aluno  = alunoService.buscarAlunoPorId(idAluno);
        return aluno.getTreinosAtribuidos();
    }

    @Override
    public List<Treino> atribuirTreinoAluno(UUID idAluno, UUID idTreino) {
        Aluno aluno = alunoService.buscarAlunoPorId(idAluno);
        Treino treino = treinoService.buscarTreino(idTreino);
        List<Treino> treinosAtuais = new ArrayList<>(aluno.getTreinosAtribuidos());
        treinosAtuais.add(treino);
        aluno.setTreinosAtribuidos(treinosAtuais);
        alunoRepository.save(aluno);
        return treinosAtuais;
    }

    @Override
    public boolean removerAluno(UUID id) {
        if(alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Aluno> buscarAlunoPorNome(String nome) {
        return alunoRepository.findByNomeUsuarioContaining(nome);
    }

    @Override
    public Aluno buscarAlunoPorId(UUID idAluno) {
        return alunoRepository.findByIdUsuario(idAluno);
    }

    @Override
    public Page<Aluno> listarAlunos(Pageable page) {
        return alunoRepository.findAll(page);
    }
}

