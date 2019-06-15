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

import br.edu.faculdadedelta.locacao.model.Fabricante;
import br.edu.faculdadedelta.locacao.model.Modelo;
import br.edu.faculdadedelta.locacao.modelo.types.Categoria;
import br.edu.faculdadedelta.locacao.service.FabricanteService;
import br.edu.faculdadedelta.locacao.service.ModeloService;

@Controller
@RequestMapping("/modelos")
public class ModeloController {

	@Autowired
	private ModeloService modeloService;

	@Autowired
	private FabricanteService fabricanteService;

	private static final String MODELO_CADASTRO = "modeloCadastro";
	private static final String MODELO_LISTA = "modeloLista";

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(MODELO_CADASTRO);
		modelAndView.addObject(new Modelo());

		return modelAndView;
	}

	@ModelAttribute(name = "todosFabricantes")
	public List<Fabricante> todosFabricantes() {
		return fabricanteService.listar();
	}

	@ModelAttribute(name = "todasCategorias")
	public Categoria[] todasCategorias() {
		return Categoria.values();
	}

	@PostMapping
	public ModelAndView salvar(@Valid Modelo modelo, Errors errors, RedirectAttributes redirectAttributes) {
		if (errors.hasErrors())
			return new ModelAndView(MODELO_CADASTRO);

		if (modelo.getId() == null) {
			modeloService.incluir(modelo);

			redirectAttributes.addFlashAttribute("mensagem", "Modelo cadastrado com sucesso!");
		} else {
			modeloService.alterar(modelo);

			redirectAttributes.addFlashAttribute("mensagem", "Modelo alterado com sucesso!");
		}

		return new ModelAndView("redirect:/modelos/novo");
	}

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView(MODELO_LISTA);
		modelAndView.addObject("modelos", modeloService.listar());

		return modelAndView;

	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(MODELO_CADASTRO);
		modelAndView.addObject(modeloService.pesquisarPorId(id));

		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView exluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/modelos");
		modeloService.excluir(id);;

		return modelAndView;
	}
}