package com.dotcom.aurora.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dotcom.aurora.model.Contato;
import com.dotcom.aurora.security.User;
import com.dotcom.aurora.service.UsuarioService;

@Controller
@CrossOrigin(origins="*")
public class IndexController {

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private UsuarioService us;
	
	private User usuario;
	
	
	@GetMapping("/")
	public String index() {
		log.info("GET index");
		return "index";
	}

	@PostMapping("/contato")
	public String contato(Contato contato) {
		log.info("POST contato");
		return "index";
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		log.info("GET login");
		ModelAndView mv = new ModelAndView("login");
		User usuario = new User();
		mv.addObject("usuario",usuario);
		return mv;
	}

	
	@GetMapping("/novousuario")
	public ModelAndView getNovoUsu() {
		log.info("GET novoUsuario");
		ModelAndView mv = new ModelAndView("novoUsuario");
		usuario = new User();
		mv.addObject("usuario",usuario);
		String msgErro = "";
		mv.addObject("msgErro",msgErro);
		return mv;
	}

	@PostMapping("/novousuario")
	public ModelAndView postNovoUsu(User nu) {
		log.info("POST novoUsuario");
		usuario = us.createUsuario(nu, 2); // 2 --> GESTOR
		ModelAndView mv = new ModelAndView("novousuario");
		mv.addObject("msgErro",us.getMsgErro());
		mv.addObject("usuario",usuario);
		return mv;
	}
	
	@GetMapping("/novousuario/{id}")
	public ModelAndView confirma(@PathVariable("id") long id) {
		log.info("GET confirma/"+id);
		usuario = us.confirmaUsuario(id);
		ModelAndView mv = new ModelAndView("novousuario");
		mv.addObject("usuario",usuario);
		return mv;
	}
	
}
