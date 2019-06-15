package br.edu.faculdadedelta.locacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.faculdadedelta.locacao.model.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

}
