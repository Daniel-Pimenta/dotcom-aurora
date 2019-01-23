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

import com.dotcom.aurora.model.Aluno;
import com.dotcom.aurora.model.Escola;
import com.dotcom.aurora.model.Turma;
import com.dotcom.aurora.service.AlunoService;
import com.dotcom.aurora.service.EscolaService;
import com.dotcom.aurora.service.TurmaService;

@Controller
@CrossOrigin(origins="*")
public class AlunoController {

	private static final Logger log = LoggerFactory.getLogger(AlunoController.class);
	
	@Autowired
	private TurmaService ts;
	@Autowired
	private EscolaService es;
	@Autowired
	private AlunoService as;
	
	@GetMapping("/aluno/listaAluno/{idTurma}")
	public ModelAndView listTurma(@PathVariable("idTurma") long idTurma) {
		log.info("GET listaAluno:"+idTurma);
		ModelAndView mv = new ModelAndView("/aluno/listaAluno");
		List<Aluno> alunos = as.getAlunos(idTurma);
		Turma turma = ts.getTurma(idTurma);
		mv.addObject("alunos",alunos);
		mv.addObject("turma",turma);
		mv.addObject("idEscola",turma.getEscola().getId());
		return mv;
	}
	
	@GetMapping("/aluno/novoAluno/{idTurma}")
	public ModelAndView novoAluno(@PathVariable("idTurma") long idTurma) {
		log.info("GET novoAluno:"+idTurma);
		ModelAndView mv = new ModelAndView("/aluno/formAluno");
		Turma turma = ts.getTurma(idTurma);
		Aluno aluno = new Aluno();
		aluno.setTurma(turma);
		mv.addObject("aluno",aluno);
		mv.addObject("turma",turma);
		return mv;
	}
	
	@PostMapping("/aluno/gravaAluno/{idTurma}")
	public ModelAndView gravaTurma(Aluno a, @PathVariable("idTurma") long idTurma) {
		log.info("POST gravaTurma:"+a.getId()+"/"+idTurma);
		a.setTurma(ts.getTurma(idTurma));
		as.saveAluno(a);
		ModelAndView mv = new ModelAndView("redirect:/aluno/listaAluno/"+idTurma);
		List<Aluno> alunos = as.getAlunos(idTurma);
		mv.addObject("alunos",alunos);
		mv.addObject("idTurma",idTurma);
		return mv;
	}
	
	@GetMapping("/aluno/editAluno/{idAluno}")
	public ModelAndView editAluno(@PathVariable("idAluno") long idAluno) {
		log.info("GET editAluno:"+idAluno);
		Aluno aluno = as.getAluno(idAluno);
		
		ModelAndView mv = new ModelAndView("/aluno/formAluno");
		mv.addObject("aluno",aluno);
		mv.addObject("idTurma",aluno.getTurma().getId());
		return mv;
	}
	
	@GetMapping("/aluno/deletaAluno/{idAluno}")
	public ModelAndView deletaAluno(@PathVariable("idAluno") long idAluno) {
		log.info("GET deletaAluno:"+idAluno);
		Aluno aluno = as.getAluno(idAluno);
		long idTurma = aluno.getTurma().getId();
		as.deletaAluno(idAluno);
		
		ModelAndView mv = new ModelAndView("redirect:/aluno/listaAluno");
		List<Aluno> alunos = as.getAlunos(idTurma);
		mv.addObject("alunos",alunos);
		mv.addObject("idTurma",idTurma);
		return mv;
	}	
}
