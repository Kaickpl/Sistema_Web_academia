package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.MedidasCorporaisRepository;
import br.com.upe.academia.AcademiaWeb.Services.MedidasCorporaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedidasCorporaisServiceImpl implements MedidasCorporaisService {
    @Autowired
    MedidasCorporaisRepository medidasCorporaisRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Override
    public List<MedidasCorporais> mostrarMedidasCorporais(UUID alunoId) {
        return medidasCorporaisRepository.findByAluno_IdUsuario(alunoId);
    }

    @Override
    public MedidasCorporais registrarMedidas(MedidasCorporaisDTOs medidasCorporaisDTOs) {
        MedidasCorporais novasMedidas = new MedidasCorporais();
        Aluno aluno = alunoRepository.findByIdUsuario(medidasCorporaisDTOs.getAlunoId());
        novasMedidas.setAluno(aluno);
        novasMedidas.setBraco(medidasCorporaisDTOs.getBraco());
        novasMedidas.setAbdomen(medidasCorporaisDTOs.getAbdomen());
        novasMedidas.setCintura(medidasCorporaisDTOs.getCintura());
        novasMedidas.setPeito(medidasCorporaisDTOs.getPeito());
        novasMedidas.setQuadril(medidasCorporaisDTOs.getQuadril());
        novasMedidas.setCoxa(medidasCorporaisDTOs.getCoxa());
        novasMedidas.setOmbro(medidasCorporaisDTOs.getOmbro());
        novasMedidas.setMassaMagra(medidasCorporaisDTOs.getMassaMagra());
        novasMedidas.setGordura(medidasCorporaisDTOs.getGordura());
        novasMedidas.setPercentualAgua(medidasCorporaisDTOs.getPercentualAgua());
        novasMedidas.setPeso(medidasCorporaisDTOs.getPeso());
        novasMedidas.setAltura(medidasCorporaisDTOs.getAltura());
        return medidasCorporaisRepository.save(novasMedidas);
    }
}
