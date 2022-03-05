package com.AppViagem.AppViagem.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppViagem.AppViagem.models.Destino;
import com.AppViagem.AppViagem.models.Viagem;
import com.AppViagem.AppViagem.repository.DestinoRepository;
import com.AppViagem.AppViagem.repository.ViagemRepository;

@Controller
public class ViagemController {

	@Autowired
	private ViagemRepository vr;
	
	@Autowired
	private DestinoRepository dr;

	// cadastrar Viagem
	@RequestMapping(value = "/cadastrarViagem", method = RequestMethod.GET)
	public String form() {

		return "viagem/formViagem";
	}

	@RequestMapping(value = "/cadastrarViagem", method = RequestMethod.POST)
	public String form(@Valid Viagem viagem, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos...");
			return "redirect:/cadastrarViagem";
		}
		vr.save(viagem);
		attributes.addFlashAttribute("mensagem", "Viagem cadastrada com sucesso!");
		return "redirect:/cadastrarViagem";
	}

	// Listar Viagens

	@RequestMapping(value = "/viagem")
	public ModelAndView listaViagem() {
		ModelAndView mv = new ModelAndView("viagem/listaViagem");
		Iterable<Viagem> viagem = vr.findAll();
		mv.addObject("viagem", viagem);
		return mv;
	}

	//
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesVaga(@PathVariable("codigo") long codigo) {
		Viagem viagem = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("viagem/descricaoViagem");
		mv.addObject("viagem", viagem);

		Iterable<Destino> destino = dr.findByViagem(viagem);
		mv.addObject("destino", destino);
		return mv;
	}

	// Deleta Vaga
	@RequestMapping("/deletarViagem")
	public String deletarViagem(long codigo) {
		Viagem viagem = vr.findByCodigo(codigo);
		vr.delete(viagem);
		return "redirect:/viagem";
	}

	public String detalhesViagemPost(@PathVariable("codigo") long codigo, @Valid Destino destino, BindingResult result,
			RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/{codigo}";
		}
		Viagem viagem = vr.findByCodigo(codigo);
		destino.setViagem(viagem);
		dr.save(destino);
		attributes.addFlashAttribute("mensgaem", "Destino adicionado com sucesso");
		return "redirect:/{codigo}";
	}

	//Deleta Destino pelo id
	
	@RequestMapping("/deletarDestino")
	public String deletarDestino(long id) {
		Destino destino = dr.findById(id);
		Viagem viagem = destino.getViagem();
		String codigo = "" + viagem.getCodigo();
		
		dr.delete(destino);
		
		return "redirect:/" + codigo;
	}
	
	
	//Metodos que atualizam a viagem
	//formulario edicao de viagem
	@RequestMapping(value="/editar-vaga", method = RequestMethod.GET)
	public ModelAndView editarViagem(long codigo) {
		Viagem viagem = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("viagem/uptade-viagem");
		mv.addObject("viagem", viagem);
		return mv;
	}
	
	//Uptade Viagem
	@RequestMapping(value="/editar-viagem", method = RequestMethod.POST)
	public String updateViagem(@Valid Viagem viagem, BindingResult result, RedirectAttributes attributes) {
		vr.save(viagem);
		attributes.addFlashAttribute("success", "Viagem alterada com sucesso!");
		
		long codigoLong = viagem.getCodigo();
		String codigo = "" +codigoLong;
		return "redirect:/" + codigo;
	}
}
	
