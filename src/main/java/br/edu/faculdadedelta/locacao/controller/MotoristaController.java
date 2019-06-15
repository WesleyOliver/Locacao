package br.edu.faculdadedelta.locacao.controller;

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

import br.edu.faculdadedelta.locacao.model.Motorista;
import br.edu.faculdadedelta.locacao.modelo.types.Sexo;
import br.edu.faculdadedelta.locacao.service.MotoristaService;

@Controller
@RequestMapping("/motoristas")
public class MotoristaController {

	@Autowired
	private MotoristaService motoristaService;

	private static final String MOTORISTA_CADASTRO = "motoristaCadastro";
	private static final String MOTORISTA_LISTA = "motoristaLista";

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(MOTORISTA_CADASTRO);
		modelAndView.addObject(new Motorista());

		return modelAndView;
	}

	@ModelAttribute(name = "todosSexos")
	public Sexo[] todosSexos() {
		return Sexo.values();
	}

	@PostMapping
	public ModelAndView salvar(@Valid Motorista motorista, Errors errors, RedirectAttributes redirectAttributes) {
		if (errors.hasErrors())
			return new ModelAndView(MOTORISTA_CADASTRO);

		if (motorista.getId() == null) {
			motoristaService.incluir(motorista);

			redirectAttributes.addFlashAttribute("mensagem", "Motorista cadastrado com sucesso!");
		} else {
			motoristaService.alterar(motorista);

			redirectAttributes.addFlashAttribute("mensagem", "Motorista alterado com sucesso!");
		}

		return new ModelAndView("redirect:/motoristas/novo");
	}

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView(MOTORISTA_LISTA);
		modelAndView.addObject("motoristas", motoristaService.listar());

		return modelAndView;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(MOTORISTA_CADASTRO);
		modelAndView.addObject(motoristaService.pesquisarPorId(id));

		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView exluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/motoristas");
		motoristaService.excluir(id);

		return modelAndView;
	}

}