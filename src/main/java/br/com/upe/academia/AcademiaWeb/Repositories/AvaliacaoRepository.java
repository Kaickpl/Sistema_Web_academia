package br.com.upe.academia.AcademiaWeb.Repositories;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {
    List<Avaliacao> findByPersonal_Cref(String personalCref);

    List<Avaliacao> findByPersonal_CrefAndDataAvaliacao(String personalCref, LocalDate dataAvaliacao);

    List<Avaliacao> findByAluno_IdUsuario(UUID alunoIdUsuario);

    List<Avaliacao> findByDataAvaliacao(LocalDate data);
}
