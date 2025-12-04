package br.com.upe.academia.AcademiaWeb.Entities;

import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno extends Usuario{
    @Column(nullable = false)
    private int saldoMoedas;

    @ManyToMany(mappedBy = "alunos")
    private List<Grupo> grupos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "aluno_treinos",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "treino_id")
    )
    private List<Treino> treinosAtribuidos = new ArrayList<>();

//    @OneToMany(mappedBy = "aluno",cascade = CascadeType.ALL)
//    private List<Aluno> alunos = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.getTipo() == Tipo.aluno){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ALUNO"),
                    new SimpleGrantedAuthority("ROLE_Usuario")
        );}
        return List.of(new SimpleGrantedAuthority("ROLE_Usuario"));
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}
