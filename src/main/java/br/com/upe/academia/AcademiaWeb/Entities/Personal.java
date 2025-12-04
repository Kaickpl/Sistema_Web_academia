package br.com.upe.academia.AcademiaWeb.Entities;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.UsuarioDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Personal extends Usuario{
    private String cref;

    @OneToMany(mappedBy = "personal",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Grupo> grupos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.getTipo() == Tipo.personalTrainer){
            return List.of(new SimpleGrantedAuthority("ROLE_PersonalTrainer"),
                    new SimpleGrantedAuthority("ROLE_Usuario")
            );
        }
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
