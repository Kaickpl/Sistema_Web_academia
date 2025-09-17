package br.com.upe.academia.AcademiaWeb.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Vendas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID IDvenda;
    private Timestamp dataVenda;
    //

}
