package br.edu.faculdadedelta.locacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.locacao.model.Fabricante;
import br.edu.faculdadedelta.locacao.repository.FabricanteRepository;

@Service
public class FabricanteService {
	
	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	public Fabricante incluir(Fabricante fabricante) {
		fabricante.setId(null);
		return fabricanteRepository.save(fabricante);
	}
	
	public Fabricante alterar(Fabricante fabricante) {
		this.pesquisarPorId(fabricante.getId());
		return fabricanteRepository.save(fabricante);
	}
	
	public void excluir(Long id) {
		fabricanteRepository.deleteById(id);
	}
	
	public List<Fabricante> listar(){
		return fabricanteRepository.findAll();
	}
	
	public Fabricante pesquisarPorId(Long id) {
		return fabricanteRepository.findById(id).orElseThrow(() 
				-> new EmptyResultDataAccessException(0));
	}
}
