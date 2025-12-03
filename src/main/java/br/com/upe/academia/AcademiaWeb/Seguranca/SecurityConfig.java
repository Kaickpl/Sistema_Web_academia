package br.com.upe.academia.AcademiaWeb.Seguranca;
import br.com.upe.academia.AcademiaWeb.Services.IMPL.UsuarioServiceImpl;
import br.com.upe.academia.AcademiaWeb.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ))
                .authorizeHttpRequests( authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/autenticacao/Login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/autenticacao/cadastro/Personal").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/autenticacao/cadastro/aluno").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/aluno/{id}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/aluno/buscarTodos").hasRole("PersonalTrainer")
                        //Grupo
                        .requestMatchers(HttpMethod.POST, "/api/Grupo").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.DELETE, "/api/Grupo/{{id}}?").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.GET, "/api/Grupo/BuscarPorNome?").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/Grupo/ListarTodos").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/Grupo/AddAluno/{{grupoId}}").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.PUT,"/api/Grupo/DeletarAluno/{{grupoId}}/{{idAluno}}").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.GET,"/api/Grupo/ListarAlunosGrupo?").hasRole("PersonalTrainer")
                        .anyRequest().authenticated()
                )
                .userDetailsService(usuarioService)
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(
                "http://localhost"
        ));
        configuration.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE"
        ));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
