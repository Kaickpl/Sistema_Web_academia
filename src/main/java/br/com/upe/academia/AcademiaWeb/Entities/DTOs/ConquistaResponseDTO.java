package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Conquistas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConquistaResponseDTO {
    private UUID idConquistas;
    private UUID alunoId;
    private String nomeConquista;
    private String descricaoConquista;
    private LocalDate data;
    private int moedas;

    public ConquistaResponseDTO(Conquistas conquistas) {
        this.alunoId = conquistas.getAluno().getIdUsuario();
        this.descricaoConquista = conquistas.getDescricaoConquista();
        this.idConquistas = conquistas.getIdConquistas();
        this.nomeConquista = conquistas.getNomeConquista();
        this.data = conquistas.getDataConquista();
        this.moedas = conquistas.getMoedas();
    }
}
