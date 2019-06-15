package br.edu.faculdadedelta.locacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.faculdadedelta.locacao.modelo.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

}
