package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import br.com.upe.academia.AcademiaWeb.Exceptions.InformacaoNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.MedidaInvalidaException;
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
        Aluno aluno = alunoRepository.findByIdUsuario(medidasCorporaisDTOs.getAlunoId());
        if (aluno == null){
            throw new InformacaoNaoEncontradoException();
        }
        //dps ver a excessão de se colocar em outro formato
        validarMedida(medidasCorporaisDTOs.getBraco(), "braço");
        validarMedida(medidasCorporaisDTOs.getAbdomen(), "abdômen");
        validarMedida(medidasCorporaisDTOs.getCintura(), "cintura");
        validarMedida(medidasCorporaisDTOs.getPeito(), "peito");
        validarMedida(medidasCorporaisDTOs.getQuadril(), "quadril");
        validarMedida(medidasCorporaisDTOs.getCoxa(), "coxa");
        validarMedida(medidasCorporaisDTOs.getOmbro(), "ombro");
        validarMedida(medidasCorporaisDTOs.getMassaMagra(), "massa magra");
        validarMedida(medidasCorporaisDTOs.getGordura(), "percentual de gordura");
        validarMedida(medidasCorporaisDTOs.getPercentualAgua(), "percentual de água");
        validarMedida(medidasCorporaisDTOs.getPeso(), "peso");
        validarMedida(medidasCorporaisDTOs.getAltura(), "altura");

        MedidasCorporais novasMedidas = new MedidasCorporais();
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

    public void validarMedida(Double valor, String nomeCampo){
        if (valor <= 0){
            throw new MedidaInvalidaException("A medida de " + nomeCampo + " deve ser maior que zero");
        }
    }
}
