package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Conquistas;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaRegistroDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistasDTOs;
import br.com.upe.academia.AcademiaWeb.Exceptions.InformacaoNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.UsuarioExistenteException;
import br.com.upe.academia.AcademiaWeb.Exceptions.UsuarioNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.ValorInvalidoException;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.ConquistasRepository;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
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
    private AlunoService alunoService;

    @Override
    public Conquistas registrarConquista(ConquistaRegistroDTO conquistasDTOs) {
        boolean existeConquista = conquistasRepository.existsByAluno_IdUsuarioAndNomeConquista(conquistasDTOs.getAlunoId(), conquistasDTOs.getNomeConquista());
        if (existeConquista){
            return null;
        }

        if (conquistasDTOs.getMoedas() <= 0){
            throw new ValorInvalidoException("O valor de moedas deve ser maior que zero");
        }

        Aluno aluno = alunoService.atualizaMoedas(conquistasDTOs.getAlunoId(), conquistasDTOs.getMoedas());

        Conquistas conquistas = new Conquistas(aluno, conquistasDTOs.getNomeConquista(), conquistasDTOs.getDescricaoConquista(), conquistasDTOs.getMoedas());
        return conquistasRepository.save(conquistas);
    }

    @Override
    public Conquistas registrarConquistaObjetivo(ConquistaRegistroDTO conquistaRegistroDTO) {
        if (conquistaRegistroDTO.getMoedas() <= 0){
            throw new ValorInvalidoException("O valor de moedas deve ser maior que zero");
        }
        Aluno aluno = alunoService.atualizaMoedas(conquistaRegistroDTO.getAlunoId(), conquistaRegistroDTO.getMoedas());
        Conquistas conquistas = new Conquistas(aluno, conquistaRegistroDTO.getNomeConquista(), conquistaRegistroDTO.getDescricaoConquista(), conquistaRegistroDTO.getMoedas());
        return conquistasRepository.save(conquistas);
    }


    @Override
    public List<ConquistaResponseDTO> mostrarConquistas(UUID alunoId) {
        Aluno aluno = alunoService.buscarAlunoPorId(alunoId);
        if (aluno == null){
            throw new UsuarioNaoEncontradoException();
        }
        List<Conquistas> conquistasList = conquistasRepository.findByAluno_IdUsuario(alunoId);
        return conquistasList.stream().map(ConquistaResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public ConquistaResponseDTO mostrarUltimaConquista(UUID alunoId) {
        Conquistas ultimaConquista = conquistasRepository.findTop1ByAluno_IdUsuarioOrderByDataConquistaDesc(alunoId);
        if (ultimaConquista == null) {
            throw new InformacaoNaoEncontradoException("O usuário " + alunoId + " não possui nenhuma conquista registrada.");
        }
        return new ConquistaResponseDTO(ultimaConquista);
    }
}
