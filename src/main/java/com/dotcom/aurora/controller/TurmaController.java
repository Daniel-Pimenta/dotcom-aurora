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
import com.dotcom.aurora.model.Turma;
import com.dotcom.aurora.service.EscolaService;
import com.dotcom.aurora.service.TurmaService;
import com.dotcom.aurora.service.UsuarioService;

@Controller
@CrossOrigin(origins="*")
public class TurmaController {
	
	private static final Logger log = LoggerFactory.getLogger(TurmaController.class);

	@Autowired
	private TurmaService ts;
	@Autowired
	private EscolaService es;
	@Autowired
	private UsuarioService us;
	
	@GetMapping("/turma/listaTurma/{idEscola}")
	public ModelAndView listTurma(@PathVariable("idEscola") long idEscola) {
		log.info("GET listaTurma:"+idEscola);
		ModelAndView mv = new ModelAndView("/turma/listaTurma");
		List<Turma> turmas = ts.getTurmas(idEscola);
		mv.addObject("turmas",turmas);
		mv.addObject("escola",es.getEscola(idEscola));
		return mv;
	}
	
	@GetMapping("/turma/novaTurma/{idEscola}")
	public ModelAndView novaTurma(@PathVariable("idEscola") long idEscola) {
		log.info("GET novaTurma:"+idEscola);
		ModelAndView mv = new ModelAndView("/turma/formTurma");
		Escola escola = es.getEscola(idEscola);
		Turma turma = new Turma();
		turma.setEscola(escola);
		mv.addObject("turma",turma);
		mv.addObject("idEscola",idEscola);
		return mv;
	}
	
	@PostMapping("/turma/gravaTurma/{idEscola}")
	public ModelAndView gravaTurma(Turma t, @PathVariable("idEscola") long idEscola) {
		log.info("POST gravaTurma:"+t.getId()+"/"+idEscola);
		t.setEscola(es.getEscola(idEscola));
		ts.saveTurma(t);
		ModelAndView mv = new ModelAndView("redirect:/turma/listaTurma/"+idEscola);
		List<Turma> turmas = ts.getTurmas(idEscola);
		mv.addObject("turmas",turmas);
		mv.addObject("idEscola",idEscola);
		return mv;
	}
	
	@GetMapping("/turma/editTurma/{idTurma}")
	public ModelAndView editTurma(@PathVariable("idTurma") long idTurma) {
		log.info("GET editTurma:"+idTurma);
		Turma turma = ts.getTurma(idTurma);
		
		ModelAndView mv = new ModelAndView("/turma/formTurma");
		mv.addObject("turma",turma);
		mv.addObject("idEscola",turma.getEscola().getId());
		return mv;
	}
	
	@GetMapping("/turma/deletaTurma/{idTurma}")
	public ModelAndView deletaTurma(@PathVariable("idTurma") long idTurma) {
		log.info("GET deletaTurma:"+idTurma);
		Turma turma = ts.getTurma(idTurma);
		long idEscola = turma.getEscola().getId();
		ts.deletaTurma(idTurma);
		
		ModelAndView mv = new ModelAndView("redirect:/turma/listaTurma");
		List<Turma> turmas = ts.getTurmas(idEscola);
		mv.addObject("turmas",turmas);
		mv.addObject("idEscola",idEscola);
		return mv;
	}	
	
	public ModelAndView minhasTurmas() {
		return null;
	}
}
