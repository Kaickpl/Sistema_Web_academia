package br.com.upe.academia.AcademiaWeb.ConquistasLogica;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas.*;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SessaoProgressaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Services.ConquistasService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GerenciaConquistas {
    private final Contexto contextoConquistas;

    public void decisaoConquista(SessaoProgressaoResponseDTO sessaoProgressaoResponseDTO, double pesoAluno){
        double peso = sessaoProgressaoResponseDTO.getPeso();
        String exercicio = sessaoProgressaoResponseDTO.getNomeExercicio();
        int repeticoes = sessaoProgressaoResponseDTO.getNumeroDeRepeticoes();
        double volumeSerie = peso * repeticoes;

        if (exercicio == null) {
            return;
        }

        if ("legpress".equalsIgnoreCase(exercicio)){
            verificarLegpress(exercicio, peso, 1.2, sessaoProgressaoResponseDTO.getAlunoId());
        }
        if ("supino inclinado".equalsIgnoreCase(exercicio)){
            verificarSupinoInclinado(exercicio, peso, 2.6, sessaoProgressaoResponseDTO.getAlunoId());
        }
        if ("elevação lateral".equalsIgnoreCase(exercicio)){
            verificarElevacaoLateral(exercicio, peso, 3.8, sessaoProgressaoResponseDTO.getAlunoId());
        }
        if ("agachamento livre".equalsIgnoreCase(exercicio)){
            verificarAgachamentoLivre(exercicio, peso, 2.2, sessaoProgressaoResponseDTO.getAlunoId());
        }
        if ("tríceps corda na polia".equalsIgnoreCase(exercicio)){
            verificarTricepsCorda(exercicio, peso, 3.4, sessaoProgressaoResponseDTO.getAlunoId());
        }
        if ("rosca martelo".equalsIgnoreCase(exercicio)){
            verificarRoscaMartelo(exercicio, peso, 2.8, sessaoProgressaoResponseDTO.getAlunoId());
        }
        if ("cadeira extensora".equalsIgnoreCase(exercicio)){
            verificarCadeiraExtensora(exercicio, peso, 1.9, sessaoProgressaoResponseDTO.getAlunoId());
        }
        if ("crucifixo com halteres".equalsIgnoreCase(exercicio)){
            verificarCrucifixo(exercicio, peso, 3.7, sessaoProgressaoResponseDTO.getAlunoId());
        }
        verificarConquistasGerais(sessaoProgressaoResponseDTO.getAlunoId(), peso, repeticoes, volumeSerie, pesoAluno);
    }

    private void verificarCrucifixo(String exercicio, double peso, double valor, UUID alunoId) {
        if (peso >= 100) {
            registrar(new ConquistasExercicios(exercicio, 100, valor), alunoId);
        }
        if (peso >= 70){
            registrar(new ConquistasExercicios(exercicio, 70, valor), alunoId);
        }
        if (peso >= 60){
            registrar(new ConquistasExercicios(exercicio, 60, valor), alunoId);
        }
        if (peso >= 40){
            registrar(new ConquistasExercicios(exercicio, 40, valor), alunoId);
        }
        if (peso >= 25){
            registrar(new ConquistasExercicios(exercicio, 25, valor), alunoId);
        }
        if(peso >= 15){
            registrar(new ConquistasExercicios(exercicio, 15, valor), alunoId);
        }
        if (peso >= 10){
            registrar(new ConquistasExercicios(exercicio, 10, valor), alunoId);
        }
    }

    private void verificarRoscaMartelo(String exercicio, double peso, double valor, UUID alunoId) {
        if (peso >= 80){
            registrar(new ConquistasExercicios(exercicio, 80, valor), alunoId);
        }
        if (peso >= 60){
            registrar(new ConquistasExercicios(exercicio, 60, valor), alunoId);
        }
        if (peso >= 40){
            registrar(new ConquistasExercicios(exercicio, 40, valor), alunoId);
        }
        if (peso >= 30){
            registrar(new ConquistasExercicios(exercicio, 30, valor), alunoId);
        }
        if (peso >= 20){
            registrar(new ConquistasExercicios(exercicio, 20, valor), alunoId);
        }
        if (peso >= 10){
            registrar(new ConquistasExercicios(exercicio, 10, valor), alunoId);
        }
    }

    private void verificarCadeiraExtensora(String exercicio, double peso, double valor, UUID alunoId) {
        if (peso >= 180) {
            registrar(new ConquistasExercicios(exercicio, 180, valor), alunoId);
        }
        if (peso >= 100){
            registrar(new ConquistasExercicios(exercicio, 100, valor), alunoId);
        }
        if (peso >= 70){
            registrar(new ConquistasExercicios(exercicio, 70, valor), alunoId);
        }
        if (peso >= 60){
            registrar(new ConquistasExercicios(exercicio, 60, valor), alunoId);
        }
        if (peso >= 40){
            registrar(new ConquistasExercicios(exercicio, 40, valor), alunoId);
        }
        if (peso >= 25){
            registrar(new ConquistasExercicios(exercicio, 25, valor), alunoId);
        }
    }

    private void verificarTricepsCorda(String exercicio, double peso, double valor, UUID alunoId) {
        if (peso >= 45){
            registrar(new ConquistasExercicios(exercicio, 45, valor), alunoId);
        }
        if (peso >= 35){
            registrar(new ConquistasExercicios(exercicio, 35, valor), alunoId);
        }
        if (peso >= 25){
            registrar(new ConquistasExercicios(exercicio, 25, valor), alunoId);
        }
    }

    public void decisaoConquistaObjetivo(UUID idUsuario, String tipoMedida) {
        ConquistasInterface conquistaObjetivo = new AtingiuObjetivo(idUsuario, tipoMedida);

        contextoConquistas.setTipo(conquistaObjetivo);
        contextoConquistas.registrarConquistaObjetivo(idUsuario);
    }

    private void verificarLegpress(String exercicio, double peso, double valor, UUID alunoId){
        if (peso >= 1000) {
            registrar(new ConquistasExercicios(exercicio, 400, valor), alunoId);
        }
        if (peso >= 200) {
            registrar(new ConquistasExercicios(exercicio, 200, valor), alunoId);
        }
        if (peso >= 150) {
            registrar(new ConquistasExercicios(exercicio, 150, valor),alunoId);
        }
        if (peso >= 100) {
            registrar(new ConquistasExercicios(exercicio, 100, valor), alunoId);
        }
    }
    private void verificarSupinoInclinado(String exercicio, double peso, double valor, UUID alunoId){
        if (peso >= 120){
            registrar(new ConquistasExercicios(exercicio, 120, valor), alunoId);
        }
        if (peso >= 90){
            registrar(new ConquistasExercicios(exercicio, 90, valor), alunoId);
        }
        if (peso >= 70) {
            registrar(new ConquistasExercicios(exercicio, 70, valor), alunoId);
        }
        if (peso >= 50) {
            registrar(new ConquistasExercicios(exercicio, 50, valor), alunoId);
        }
        if (peso >= 20) {
            registrar(new ConquistasExercicios(exercicio, 20, valor), alunoId);
        }
    }
    private void verificarElevacaoLateral(String exercicio, double peso, double valor, UUID alunoId){
        if (peso >= 60){
            registrar(new ConquistasExercicios(exercicio, 60, valor), alunoId);
        }
        if (peso >= 40){
            registrar(new ConquistasExercicios(exercicio, 40, valor), alunoId);
        }
        if (peso >= 25){
            registrar(new ConquistasExercicios(exercicio, 60, valor), alunoId);
        }
        if (peso >= 10) {
            registrar(new ConquistasExercicios(exercicio, 10, valor), alunoId);
        }
        if (peso >= 5){
            registrar(new ConquistasExercicios(exercicio, 5, valor), alunoId);
        }
    }
    private void verificarAgachamentoLivre(String exercicio, double peso, double valor, UUID alunoId){
        if (peso >= 150) {
            registrar(new ConquistasExercicios(exercicio, 150, valor), alunoId);
        }
        if (peso >= 100){
            registrar(new ConquistasExercicios(exercicio, 100, valor), alunoId);
        }
        if (peso >= 50) {
            registrar(new ConquistasExercicios(exercicio, 50, valor), alunoId);
        }
    }
    private void verificarConquistasGerais(UUID alunoId, double peso, int reps, double volume, double pesoAluno){
        if (reps >= 20) {
            registrar(new Resistencia(),alunoId);
        }
        if (volume > 500) {
            registrar(new Volume500(), alunoId);
        }
        if (pesoAluno > 0 && peso > (pesoAluno * 3)) {
            registrar(new Formiguinha(), alunoId);
        }
    }

    private void registrar(ConquistasInterface conquista, UUID alunoId){
        contextoConquistas.setTipo(conquista);
        contextoConquistas.registrar(alunoId);
    }

}
