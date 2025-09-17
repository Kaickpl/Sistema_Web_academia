package br.com.upe.academia.AcademiaWeb.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID IDproduto;
    private String nomeProduto;
    private String categoriaProduto;
    private String  descricaoProduto;
    private double precoVenda;

}
