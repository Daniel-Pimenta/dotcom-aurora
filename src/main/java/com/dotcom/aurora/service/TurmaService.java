package com.dotcom.aurora.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotcom.aurora.model.Escola;
import com.dotcom.aurora.model.Turma;
import com.dotcom.aurora.repository.EscolaRepository;
import com.dotcom.aurora.repository.TurmaRepository;

@Service
public class TurmaService {
	
	private static final Logger log = LoggerFactory.getLogger(TurmaService.class);
	
	@Autowired
	TurmaRepository tr;
	@Autowired
	private EscolaRepository er;
	@Autowired
	UsuarioService us;
	
	public Turma getTurma(long idTurma){
		Optional optTurma = tr.findById(idTurma);
    return optTurma.isPresent() ? (Turma) optTurma.get() : new Turma();
	}
	
	public List<Turma> getTurmas(long idEscola){
		Optional optEscola = er.findById(idEscola);
    return tr.findAllByEscola(optEscola.isPresent() ? (Escola) optEscola.get() : new Escola());
	}
	
	public Turma saveTurma(Turma t) {
		Turma turma = tr.save(t);
		return turma;
	}
	
	public void deletaTurma(long idTurma) {
		tr.deleteById(idTurma);
	}
	
}
