package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.GerenciaConquistas;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SessaoProgressaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import br.com.upe.academia.AcademiaWeb.Entities.Objetivos;
import br.com.upe.academia.AcademiaWeb.Repositories.MedidasCorporaisRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.ObjetivosRepository;
import br.com.upe.academia.AcademiaWeb.Services.AnaliseDesempenhoService;
import br.com.upe.academia.AcademiaWeb.utils.MedidasUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnaliseDesempenhoServiceImpl implements AnaliseDesempenhoService {
    @Autowired
    private final GerenciaConquistas gerenciaConquistas;

    @Autowired
    private final MedidasCorporaisRepository medidasRepository;

    @Autowired
    private final ObjetivosRepository objetivosRepository;

    @Override
    public void processarDesempenhoSerie(SessaoProgressaoResponseDTO progressoDTO) {
        MedidasCorporais ultimaMedida = medidasRepository.findTop1ByAluno_IdUsuarioOrderByDataDesc(progressoDTO.getAlunoId());

        double pesoAluno = (ultimaMedida != null) ? ultimaMedida.getPeso() : 0.0;

        gerenciaConquistas.decisaoConquista(progressoDTO, pesoAluno);

        UUID alunoId = progressoDTO.getAlunoId();
        String nomeExercicio = progressoDTO.getNomeExercicio();
        double cargaLevantada = progressoDTO.getPeso();

        List<Objetivos> objetivosPendentes = objetivosRepository.findAllByAluno_IdUsuarioAndTipoMedidaAndConcluido(alunoId, nomeExercicio, false);

        for (Objetivos objetivo : objetivosPendentes) {
            if (cargaLevantada > objetivo.getValorAtual()) {
                objetivo.setValorAtual(cargaLevantada);
                if (cargaLevantada >= objetivo.getValorAlvo()){
                    objetivo.setConcluido(true);

                    gerenciaConquistas.decisaoConquistaObjetivo(alunoId, nomeExercicio);
                }
                objetivosRepository.save(objetivo);
            }
        }
    }
    @Override
    public void processarNovasMedidas(MedidasCorporais novaMedida) {
        UUID alunoId = novaMedida.getAluno().getIdUsuario();
        List<Objetivos> objetivosPendentes = objetivosRepository.findAllByAluno_IdUsuarioAndConcluido(alunoId, false);

        for (Objetivos objetivo : objetivosPendentes) {
            Double novoValor = MedidasUtils.getValorPorNome(novaMedida, objetivo.getTipoMedida());

            if (novoValor != null && novoValor > 0) {
                double valorAntigo = objetivo.getValorAtual();
                boolean eraPraAumentar = valorAntigo < objetivo.getValorAlvo();
                boolean estavaConcluidoAntes = objetivo.isConcluido();

                objetivo.setValorAtual(novoValor);

                boolean concluido;
                if (eraPraAumentar) {
                    concluido = novoValor >= objetivo.getValorAlvo();
                } else {
                    concluido = novoValor <= objetivo.getValorAlvo();
                }

                objetivo.setConcluido(concluido);

                objetivosRepository.save(objetivo);

                if (!estavaConcluidoAntes && concluido) {
                    gerenciaConquistas.decisaoConquistaObjetivo(alunoId, objetivo.getTipoMedida());
                }
        }
    }
}}
