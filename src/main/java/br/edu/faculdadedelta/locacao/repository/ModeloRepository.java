package br.edu.faculdadedelta.locacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.faculdadedelta.locacao.modelo.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {

}
