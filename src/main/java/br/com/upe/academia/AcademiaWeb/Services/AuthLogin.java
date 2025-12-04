package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.LoginDTOs;
import org.springframework.stereotype.Service;

@Service
public interface AuthLogin {
    String login(LoginDTOs loginDTOs);
}
