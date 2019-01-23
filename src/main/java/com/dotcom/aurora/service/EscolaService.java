package com.dotcom.aurora.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotcom.aurora.model.Escola;
import com.dotcom.aurora.repository.EscolaRepository;
import com.dotcom.aurora.security.User;
import com.dotcom.aurora.security.UserRepository;

@Service
public class EscolaService {
	
	private static final Logger log = LoggerFactory.getLogger(EscolaService.class);

	@Autowired
	private UserRepository ur;
	@Autowired
	private EscolaRepository er;
	@Autowired
	UsuarioService us;
	
	public List<Escola> saveEscola(Escola escola) {
		User user = us.getUserLogedin();
		escola.setIdGestor(user.getUserId());
		er.save(escola);
		List<Escola> escolas = er.findAllByGestor(user.getUsername());
		return escolas;
	}
	
	public List<Escola> getEscolas() {
		List<Escola> escolas = er.findAllByGestor(us.getUserLogedin().getUsername());
		return escolas;
	}

	public Escola getEscola(long idEscola) {
		Optional<Escola> escola = er.findById(idEscola);
		return escola.isPresent() ? escola.get() : new Escola();
	}
	
	public List<Escola> removeEscola(long idEscola) {
		er.deleteById(idEscola);
		List<Escola> escolas = er.findAllByGestor(us.getUserLogedin().getUsername());
		return escolas;
	}
	
}
