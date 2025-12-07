package br.com.upe.academia.AcademiaWeb.Seguranca;
import br.com.upe.academia.AcademiaWeb.Services.IMPL.UsuarioServiceImpl;
import br.com.upe.academia.AcademiaWeb.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> {})
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ))
                .authorizeHttpRequests( authorize -> authorize
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/autenticacao/Login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/autenticacao/cadastro/Personal").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/autenticacao/cadastro/aluno").permitAll()

                        //Grupo
                        .requestMatchers(HttpMethod.POST, "/api/Grupo").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.DELETE, "/api/Grupo/{id}").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.GET, "/api/Grupo/BuscarPorNome").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/Grupo/ListarTodos").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/Grupo/AddAluno/{grupoId}").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.PUT,"/api/Grupo/DeletarAluno/{grupoId}/{idAluno}").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.GET,"/api/Grupo/ListarAlunosGrupo").hasRole("PersonalTrainer")

                        //Aluno
                        .requestMatchers(HttpMethod.DELETE,"/api/aluno/{id}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET,"/api/aluno/buscar").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.PUT,"/api/aluno/{id}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.PUT, "/api/aluno/RecuperarSeha").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET,"/api/aluno/ListarGruposAluno/{idAluno}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/aluno/VerPerfil/{idAluno}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/aluno/buscarTodos").hasRole("PersonalTrainer")

                        //Personal
                        .requestMatchers(HttpMethod.DELETE,"api/personal/{id}").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.PUT,"/api/personal/{id}").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.PUT,"/api/personal/RecuperarSenha").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.GET,"/api/personal/ListarGruposPersonal/{idPersonal}").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.GET, "/api/personal/buscar").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET,"/api/personal/buscarCref").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/personal/VerPerfil/{id}").hasRole("PersonalTrainer")

                        //avaliacao
                        .requestMatchers(HttpMethod.GET, "/api/avaliacao/aluno/{alunoId}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/avaliacao/aluno/{alunoId}/proxima").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/avaliacao/personal").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.GET, "/api/avaliacao/personal/data").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.DELETE, "/api/avaliacao/{idAvaliacao}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.POST, "/api/avaliacao").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.PUT, "/api/avaliacao/{idAvaliacao}/atualizar/data").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.PUT, "/api/avaliacao/{idAvaliacao}/atualizar/personal").hasRole("ALUNO")

                        //objetivo
                        .requestMatchers(HttpMethod.GET, "/api/objetivos/{alunoId}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/objetivos/{alunoId}/concluidos").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/objetivos/{alunoId}/nao-concluidos").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.POST, "/api/objetivos/medidas").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.POST, "/api/objetivos/exercicios/{idExercicio}").hasRole("ALUNO")

                        //conquistas
                        .requestMatchers(HttpMethod.GET, "/api/conquistas/{alunoId}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/conquistas/{alunoId}/recente").hasRole("ALUNO")

                        //medidas corporais
                        .requestMatchers(HttpMethod.GET, "/api/medidas/{alunoId}/historico").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/medidas/{alunoId}/historico/10").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/medidas/{alunoId}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.POST, "/api/medidas").hasRole("ALUNO")

                        //treino
                        .requestMatchers(HttpMethod.GET, "/api/treino/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/treino/aluno/{idAluno}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.PUT, "/api/treino/{id}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.DELETE, "/api/treino/{id}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/treino/{id}/completo").permitAll()

                        //exercicio
                        .requestMatchers(HttpMethod.GET, "/api/exercicio/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/exercicio").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.PUT, "/api/exercicio/{id}").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.DELETE, "/api/exercicio/{id}").hasRole("PersonalTrainer")
                        .requestMatchers(HttpMethod.GET, "/api/exercicio").permitAll()


                        // Serie
                        .requestMatchers(HttpMethod.GET, "/api/exerciciosTemplate/{idTreinoExercicio}/seriesTemplate/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/exerciciosTemplate/{idTreinoExercicio}/seriesTemplate").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.DELETE, "/api/exerciciosTemplate/{idTreinoExercicio}/seriesTemplate/{id}").hasRole("ALUNO")

                        //treinoExercicio
                        .requestMatchers(HttpMethod.POST, "/api/treino-exercicios/treinos/{idTreino}/exercicios/{idExercicio}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.PUT, "/api/treino-exercicios/{idTreinoExercicio}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.DELETE, "/api/treino-exercicios/{idTreinoExercicio}").hasRole("ALUNO")

                        //treinosSessao
                        .requestMatchers(HttpMethod.POST, "/api/alunos/{idAluno}/treinos/{idTreinoTemplate}/sessoes").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.PUT, "/api/alunos/{idAluno}/{idTreinoSessao}/fechar").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.PUT, "/api/alunos/{idAluno}/{idTreinoSessao}/comentar").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.DELETE, "/api/alunos/{idAluno}/{idTreinoSessao}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/alunos/{idAluno}/{idTreinoSessao}").permitAll()

                        //exercicioSessao
                        .requestMatchers(HttpMethod.POST, "/api/sessoes/{idSessao}/exercicios").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.DELETE, "/api/sessoes/{idSessao}/exercicios/{idExercicioSessao}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/sessoes/{idSessao}/exercicios/{idExercicioSessao}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/sessoes/{idSessao}/exercicios/{idExercicioSessao}").hasRole("ALUNO")

                        //serieSessao
                        .requestMatchers(HttpMethod.POST, "/api/exercicios/{idExercicioSessao}/series").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.DELETE, "/api/exercicios/{idExercicioSessao}/series/{idSerieSessao}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/exercicios/{idExercicioSessao}/series/{idSerieSessao}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/exercicios/{idExercicioSessao}/series/{idSerieSessao}").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/exercicios/{idExercicioSessao}/series/{idAluno}/recordes/{idExercicio}").permitAll()

                        //treinos_alunos
                        .requestMatchers(HttpMethod.POST, "/api/aluno/{idAluno}/treinos").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/api/aluno/{idAluno}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/aluno/{idAluno}/treinos/{idTreino}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/aluno/{idAluno}/treinos/{idTreino}").hasRole("ALUNO")

                        //command
                        .requestMatchers(HttpMethod.POST, "/api/historico/desfazer").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/historico/refazer").permitAll()

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
                "*"
        ));
        configuration.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT"
        ));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
