package br.edu.faculdadedelta.locacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.locacao.model.Locacao;
import br.edu.faculdadedelta.locacao.repository.LocacaoRepository;

@Service
public class LocacaoService {

	@Autowired
	private LocacaoRepository locacaoRepository;

	public Locacao incluir(Locacao locacao) {
		locacao.setId(null);
		return locacaoRepository.save(locacao);
	}

	public Locacao alterar(Locacao locacao) {
		this.pesquisarPorId(locacao.getId());
		return locacaoRepository.save(locacao);
	}

	public void excluir(Long id) {
		locacaoRepository.deleteById(id);
	}

	public List<Locacao> listar() {
		return locacaoRepository.findAll();
	}

	public Locacao pesquisarPorId(Long id) {
		return locacaoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0));
	}

}
