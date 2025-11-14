package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Conquistas;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaRegistroDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaResponseDTO;
import br.com.upe.academia.AcademiaWeb.Exceptions.ConquistaRegistradaException;
import br.com.upe.academia.AcademiaWeb.Exceptions.UsuarioNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.ValorInvalidoException;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.ConquistasRepository;
import br.com.upe.academia.AcademiaWeb.Services.ConquistasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConquistasServiceImpl implements ConquistasService {
    @Autowired
    ConquistasRepository conquistasRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Override
    public Conquistas registrarConquista(ConquistaRegistroDTO conquistaRegistroDTO) {

        Aluno aluno = alunoRepository.findByIdUsuario(conquistaRegistroDTO.getAlunoId());
        if (aluno == null){
            throw new UsuarioNaoEncontradoException();
        }
        if (conquistaRegistroDTO.getMoedas() <= 0){
            throw new ValorInvalidoException("O valor de moedas deve ser maior que zero");
        }
        boolean existeConquista = conquistasRepository.existsByAluno_IdUsuarioAndNomeConquista(conquistaRegistroDTO.getAlunoId(), conquistaRegistroDTO.getNomeConquista());
        if (existeConquista){
            throw new ConquistaRegistradaException(conquistaRegistroDTO.getAlunoId(), conquistaRegistroDTO.getNomeConquista());
        }

        int novoSaldo = aluno.getSaldoMoedas() + conquistaRegistroDTO.getMoedas();

        aluno.setSaldoMoedas(novoSaldo);

        alunoRepository.save(aluno);

        Conquistas conquistas = new Conquistas(aluno, conquistaRegistroDTO.getNomeConquista(), conquistaRegistroDTO.getDescricaoConquista(), conquistaRegistroDTO.getMoedas());

        return conquistasRepository.save(conquistas);
    }

    @Override
    public List<ConquistaResponseDTO> mostrarConquistas(UUID alunoId) {
        Aluno aluno = alunoRepository.findByIdUsuario(alunoId);
        if (aluno == null){
            throw new UsuarioNaoEncontradoException();
        }
        List<Conquistas> conquistas = conquistasRepository.findByAluno_IdUsuario(alunoId);

        return conquistas.stream().map(ConquistaResponseDTO::new).collect(Collectors.toList());
    }
}
