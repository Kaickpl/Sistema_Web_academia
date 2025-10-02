package br.com.upe.academia.AcademiaWeb.Repositories;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Entities.SolicitacaoAvaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;
@Repository

public interface SolicitacaoAvaliacaoRepository extends JpaRepository<SolicitacaoAvaliacao, UUID> {
    SolicitacaoAvaliacao findByIdAvaliacao(UUID avaliacao);
    SolicitacaoAvaliacao findByDataSolicitacao(Date SolicitacaoAvaliacao);
    SolicitacaoAvaliacao findByPersonal(Personal personal);

}
