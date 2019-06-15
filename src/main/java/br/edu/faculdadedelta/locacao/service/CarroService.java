package br.edu.faculdadedelta.locacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.locacao.model.Carro;
import br.edu.faculdadedelta.locacao.repository.CarroRepository;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;

	public Carro incluir(Carro carro) {
		carro.setId(null);
		return carroRepository.save(carro);
	}

	public Carro alterar(Carro carro) {
		this.pesquisarPorId(carro.getId());
		return carroRepository.save(carro);
	}

	public void excluir(Long id) {
		carroRepository.deleteById(id);
	}

	public List<Carro> listar() {
		return carroRepository.findAll();
	}

	public Carro pesquisarPorId(Long id) {
		return carroRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0));
	}

}
