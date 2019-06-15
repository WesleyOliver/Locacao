package br.edu.faculdadedelta.locacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.locacao.model.Modelo;
import br.edu.faculdadedelta.locacao.repository.ModeloRepository;

@Service
public class ModeloService {
	
	@Autowired
	private ModeloRepository modeloRepository;
	
	public Modelo incluir(Modelo modelo) {
		modelo.setId(null);
		return modeloRepository.save(modelo);
	}
	
	public Modelo alterar(Modelo modelo) {
		this.pesquisarPorId(modelo.getId());
		return modeloRepository.save(modelo);
	}
	
	public void excluir(Long id) {
		modeloRepository.deleteById(id);
	}
	
	public List<Modelo> listar(){
		return modeloRepository.findAll();
	}
	
	public Modelo pesquisarPorId(Long id) {
		return modeloRepository.findById(id).orElseThrow(() 
				-> new EmptyResultDataAccessException(0));
	}

}
