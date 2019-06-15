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

import br.edu.faculdadedelta.locacao.modelo.Fabricante;
import br.edu.faculdadedelta.locacao.modelo.Modelo;
import br.edu.faculdadedelta.locacao.modelo.types.Categoria;
import br.edu.faculdadedelta.locacao.repository.FabricanteRepository;
import br.edu.faculdadedelta.locacao.repository.ModeloRepository;

@Controller
@RequestMapping("/modelos")
public class ModeloController {

	@Autowired
	private ModeloRepository modeloRepository;

	@Autowired
	private FabricanteRepository fabricanteRepository;

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
		return fabricanteRepository.findAll();
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
			modeloRepository.save(modelo);

			redirectAttributes.addFlashAttribute("mensagem", "Modelo cadastrado com sucesso!");
		} else {
			modeloRepository.save(modelo);

			redirectAttributes.addFlashAttribute("mensagem", "Modelo alterado com sucesso!");
		}

		return new ModelAndView("redirect:/modelos/novo");
	}

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView(MODELO_LISTA);
		modelAndView.addObject("modelos", modeloRepository.findAll());

		return modelAndView;

	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(MODELO_CADASTRO);
		modelAndView.addObject(modeloRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0)));

		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView exluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/modelos");
		modeloRepository.deleteById(id);

		return modelAndView;
	}
}