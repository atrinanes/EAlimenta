package br.com.fiap.ambers.EAlimentos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import br.com.fiap.ambers.EAlimentos.model.Alimento;
import br.com.fiap.ambers.EAlimentos.repository.AlimentoRepository;

@RequestMapping("/alimento")
@Controller
public class AlimentoController {

	@Autowired
	private MessageSource messages;

	@Autowired
	private AlimentoRepository repository;

	@GetMapping
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("alimentos");
		List<Alimento> alimentos = repository.findAll();
		modelAndView.addObject("alimentos", alimentos);
		return modelAndView;
	}

	@GetMapping
	@RequestMapping("/novo")
	public String incluir(Alimento alimento) {
		return "alimento-form";
	}

	@GetMapping
	@RequestMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("alimento", repository.findById(id).get());
		return "alimento-form";
	}

	@PutMapping
	public String editar(Alimento alimento) {

		// TODO: Salvar
		System.out.println("Alimento salvo: " + alimento.getNome());

		return "alimento-form";
	}

	@PostMapping
	public String gravar(@Valid Alimento alimento, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors())
			return "alimento-form";

		alimento.setDataCadastro(LocalDate.now());
		repository.save(alimento);

		System.out.println("Alimento salvo: " + alimento.getNome());
		redirect.addFlashAttribute("message",
				messages.getMessage("message.success.novoalimento", null, LocaleContextHolder.getLocale()));
		return "redirect:/alimento";
	}

	@DeleteMapping("/deletar/{id}")
	public String deletar(@PathVariable("id") Long id, RedirectAttributes redirect) {
		Alimento alimento = repository.findById(id).get();

		repository.delete(alimento);

		redirect.addFlashAttribute("message",
				messages.getMessage("message.success.excluiralimento", null, LocaleContextHolder.getLocale()));
		return "redirect:/alimento";
	}
}
