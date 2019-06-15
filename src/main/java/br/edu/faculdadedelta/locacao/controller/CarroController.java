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
import br.edu.faculdadedelta.locacao.modelo.Modelo;
import br.edu.faculdadedelta.locacao.repository.CarroRepository;
import br.edu.faculdadedelta.locacao.repository.ModeloRepository;

@Controller
@RequestMapping("/carros")
public class CarroController {

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private ModeloRepository modeloRepository;

	private static final String CARRO_CADASTRO = "carroCadastro";
	private static final String CARRO_LISTA = "carroLista";

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(CARRO_CADASTRO);
		modelAndView.addObject(new Carro());

		return modelAndView;
	}

	@ModelAttribute(name = "todosModelos")
	public List<Modelo> todosModelos() {
		return modeloRepository.findAll();
	}

	@PostMapping
	public ModelAndView salvar(@Valid Carro carro, Errors errors, RedirectAttributes redirectAttributes) {
		if (errors.hasErrors())
			return new ModelAndView(CARRO_CADASTRO);
		if (carro.getId() == null) {
			carroRepository.save(carro);

			redirectAttributes.addFlashAttribute("mensagem", "Carro cadastrado com sucesso!");
		} else {
			carroRepository.save(carro);

			redirectAttributes.addFlashAttribute("mensagem", "Carro alterado com sucesso!");
		}

		return new ModelAndView("redirect:/carros/novo");
	}

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView(CARRO_LISTA);
		modelAndView.addObject("carros", carroRepository.findAll());

		return modelAndView;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(CARRO_CADASTRO);
		modelAndView.addObject(carroRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0)));

		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carros");
		carroRepository.deleteById(id);

		return modelAndView;
	}

}
