package br.com.upe.academia.AcademiaWeb.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Personal extends Usuario{
    private String cref;

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Grupo> grupos;

}
