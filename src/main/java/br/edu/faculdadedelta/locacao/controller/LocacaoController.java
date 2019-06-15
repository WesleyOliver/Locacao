package br.edu.faculdadedelta.locacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.faculdadedelta.locacao.model.Carro;
import br.edu.faculdadedelta.locacao.model.Locacao;
import br.edu.faculdadedelta.locacao.model.Motorista;
import br.edu.faculdadedelta.locacao.service.CarroService;
import br.edu.faculdadedelta.locacao.service.LocacaoService;
import br.edu.faculdadedelta.locacao.service.MotoristaService;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {

	@Autowired
	private LocacaoService locacaoService;

	@Autowired
	private MotoristaService motoristaService;

	@Autowired
	private CarroService carroService;

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
		return carroService.listar();
	}

	@ModelAttribute(name = "todosMotoristas")
	public List<Motorista> todosMotoristas() {
		return motoristaService.listar();
	}

	@PostMapping
	public ModelAndView salvar(@Valid Locacao locacao, Errors errors, RedirectAttributes redirectAttributes) {
		if (errors.hasErrors())
			return new ModelAndView(LOCACAO_CADASTRO);
		if (locacao.getId() == null) {
			locacaoService.incluir(locacao);

			redirectAttributes.addFlashAttribute("mensagem", "Locação cadastrado com sucesso!");
		} else {
			locacaoService.alterar(locacao);

			redirectAttributes.addFlashAttribute("mensagem", "Locação alterado com sucesso!");
		}

		return new ModelAndView("redirect:/locacoes/novo");
	}

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView(LOCACAO_LISTA);
		modelAndView.addObject("locacoes", locacaoService.listar());

		return modelAndView;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(LOCACAO_CADASTRO);
		modelAndView.addObject(locacaoService.pesquisarPorId(id));

		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/locacoes");
		locacaoService.excluir(id);

		return modelAndView;
	}

}
