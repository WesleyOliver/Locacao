package br.edu.faculdadedelta.locacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.faculdadedelta.locacao.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {

}
