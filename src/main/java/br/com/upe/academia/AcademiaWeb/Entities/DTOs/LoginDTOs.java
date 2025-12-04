package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class LoginDTOs {
    private String email;
    private String password;

    public LoginDTOs(String email, String password) {
        this.email = email;
        this.password = password;

    }
}
