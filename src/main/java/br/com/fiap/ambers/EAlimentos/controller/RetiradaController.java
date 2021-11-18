package br.com.fiap.ambers.EAlimentos.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.ambers.EAlimentos.model.Alimento;
import br.com.fiap.ambers.EAlimentos.model.Retirada;
import br.com.fiap.ambers.EAlimentos.repository.AlimentoRepository;
import br.com.fiap.ambers.EAlimentos.repository.RetiradaRepository;

@RequestMapping("/retirada")
@Controller
public class RetiradaController {
	
	@Autowired
	private AlimentoRepository alimentoRepository;
	
	@Autowired
	private RetiradaRepository retiradaRepository;
	
	@Autowired
	private MessageSource messages;
	
	@GetMapping
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("retiradas");
		List<Retirada> retiradas = retiradaRepository.findAll();
		modelAndView.addObject("retiradas", retiradas);
		return modelAndView;
	}
	
	@GetMapping
	@RequestMapping("/{id}")
	public String novaRetirada(@PathVariable("id") Long id, Model model) {
		
		Alimento alimento = alimentoRepository.findById(id).get();
		
		model.addAttribute("alimento", alimento);
		model.addAttribute("retirada", new Retirada(alimento));
		
		return "retirada-form";
	}
	
	@PostMapping
	public String gravar(@Valid Retirada retirada, Alimento alimento, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors())
			return "retirada-form";

		System.out.println(alimento.getNome());
		retirada.setDataRetirada(LocalDate.now());
		try {
			retirada.getAlimento().setQuantidade(this.reduzirQuantidade(retirada));
		} catch (Exception e) {
			redirect.addFlashAttribute("message",
					messages.getMessage("message.error.retiradanaopermitidaquantidade", null, LocaleContextHolder.getLocale()));
			return "redirect:/retirada/" + retirada.getAlimento().getId();
		}
		
		retiradaRepository.save(retirada);

		System.out.println("Alimento retirado: " + retirada.getAlimento().getNome());
		redirect.addFlashAttribute("message",
				messages.getMessage("message.success.novaretirada", null, LocaleContextHolder.getLocale()));
		return "redirect:/alimento"; 
	}
	
	public Long reduzirQuantidade(Retirada retirada) throws Exception {
		Long quantidadeAtual = retirada.getAlimento().getQuantidade();
		Long novaQuantidade = quantidadeAtual - retirada.getQuantidadeRetirados();
		
		if (novaQuantidade < 0)
			throw new Exception("Não é permitido retirar mais produtos que o disponível.");
		
		return novaQuantidade;
	}

}
