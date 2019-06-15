package br.edu.faculdadedelta.locacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.locacao.model.Motorista;
import br.edu.faculdadedelta.locacao.repository.MotoristaRepository;

@Service
public class MotoristaService {

	@Autowired
	private MotoristaRepository motoristaRepository;

	public Motorista incluir(Motorista motorista) {
		motorista.setId(null);
		return motoristaRepository.save(motorista);
	}

	public Motorista alterar(Motorista motorista) {
		this.pesquisarPorId(motorista.getId());
		return motoristaRepository.save(motorista);
	}

	public void excluir(Long id) {
		motoristaRepository.deleteById(id);
	}

	public List<Motorista> listar() {
		return motoristaRepository.findAll();
	}

	public Motorista pesquisarPorId(Long id) {
		return motoristaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0));
	}

}
