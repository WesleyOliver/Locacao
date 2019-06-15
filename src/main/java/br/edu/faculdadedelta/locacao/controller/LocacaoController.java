package br.edu.faculdadedelta.locacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.faculdadedelta.locacao.modelo.Carro;
import br.edu.faculdadedelta.locacao.modelo.Locacao;
import br.edu.faculdadedelta.locacao.modelo.Motorista;
import br.edu.faculdadedelta.locacao.repository.CarroRepository;
import br.edu.faculdadedelta.locacao.repository.LocacaoRepository;
import br.edu.faculdadedelta.locacao.repository.MotoristaRepository;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {

	@Autowired
	private LocacaoRepository locacaoRepository;

	@Autowired
	private MotoristaRepository motoristaRepository;

	@Autowired
	private CarroRepository carroRepository;

	private static final String LOCACAO_CADASTRO = "locacaoCadastro";
	private static final String LOCACAO_LISTA = "locacaoLista";

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(LOCACAO_CADASTRO);
		modelAndView.addObject(new Locacao());

		return modelAndView;
	}

	@ModelAttribute(name = "todosCarros")
	public List<Carro> todosCarros() {
		return carroRepository.findAll();
	}

	@ModelAttribute(name = "todosMotoristas")
	public List<Motorista> todosMotoristas() {
		return motoristaRepository.findAll();
	}

	@PostMapping
	public ModelAndView salvar(@Valid Locacao locacao, Errors errors, RedirectAttributes redirectAttributes) {
		if (errors.hasErrors())
			return new ModelAndView(LOCACAO_CADASTRO);
		if (locacao.getId() == null) {
			locacaoRepository.save(locacao);

			redirectAttributes.addFlashAttribute("mensagem", "Locação cadastrado com sucesso!");
		} else {
			locacaoRepository.save(locacao);

			redirectAttributes.addFlashAttribute("mensagem", "Locação alterado com sucesso!");
		}

		return new ModelAndView("redirect:/locacoes/novo");
	}

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView(LOCACAO_LISTA);
		modelAndView.addObject("locacoes", locacaoRepository.findAll());

		return modelAndView;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(LOCACAO_CADASTRO);
		modelAndView.addObject(locacaoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0)));

		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/locacoes");
		locacaoRepository.deleteById(id);

		return modelAndView;
	}

}
