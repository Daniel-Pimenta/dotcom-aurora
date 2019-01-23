package com.dotcom.aurora.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dotcom.aurora.model.Escola;
import com.dotcom.aurora.service.EscolaService;

@Controller
@CrossOrigin(origins="*")
public class EscolaController {
	
	private static final Logger log = LoggerFactory.getLogger(EscolaController.class);

	@Autowired
	private EscolaService es;

	@GetMapping("/escola/listaEscola")
	public ModelAndView listEscola() {
		log.info("GET ListaEscola");
		ModelAndView mv = new ModelAndView("/escola/listaEscola");
		List<Escola> escolas = es.getEscolas();
		mv.addObject("escolas",escolas);
		return mv;
	}

	// Retorna um formulario vazio para cadastro
	@GetMapping("/escola/formEscola")
	public ModelAndView formEscola() {
		log.info("GET formEscola");
		ModelAndView mv = new ModelAndView("/escola/formEscola");
		Escola escola = new Escola();
		mv.addObject("escola",escola);
		return mv;
	}
	
  //Retorna um formulario com uma escola para edição
	@GetMapping("/escola/formEscola/{idEscola}")
	public ModelAndView formEscola(@PathVariable("idEscola") long idEscola) {
		log.info("GET formEscola:"+idEscola);
		ModelAndView mv = new ModelAndView("/escola/formEscola");
		Escola escola = es.getEscola(idEscola);
		mv.addObject("escola",escola);
		return mv;
	}
	
	@PostMapping("/escola/gravaEscola")
	public ModelAndView gravaEscola(Escola escola) {
		log.info("POST gravaEscola{" + escola.getId() + "}");
		ModelAndView mv = new ModelAndView("redirect:/escola/listaEscola");
    List<Escola> escolas = es.saveEscola(escola);
		mv.addObject("escolas",escolas);
		return mv;
	}
	
	@GetMapping("/escola/deletaEscola/{idEscola}")
	public ModelAndView deletaEscola(@PathVariable("idEscola") long idEscola) {
		log.info("GET deletaEscola:"+idEscola);
		ModelAndView mv = new ModelAndView("redirect:/escola/listaEscola");
    List<Escola> escolas = es.removeEscola(idEscola);
		mv.addObject("escolas",escolas);
		return mv;
	}
	
}
