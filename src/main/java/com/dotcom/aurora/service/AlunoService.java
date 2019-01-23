package com.dotcom.aurora.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotcom.aurora.model.Aluno;
import com.dotcom.aurora.model.Turma;
import com.dotcom.aurora.repository.AlunoRepository;
import com.dotcom.aurora.repository.TurmaRepository;

@Service
public class AlunoService {
	
	private static final Logger log = LoggerFactory.getLogger(AlunoService.class);
	
	@Autowired
	AlunoRepository ar;
	@Autowired
	TurmaRepository tr;
	@Autowired
	UsuarioService us;
	
	public List<Aluno> getAlunos(long idTurma){
		Optional<Turma> optTurma = tr.findById(idTurma);
		Turma turma = optTurma.isPresent() ? optTurma.get() : new Turma();
		return ar.findAllByTurma(turma);
	}

	public Aluno getAluno(long idAluno){
		Optional<Aluno> optAluno = ar.findById(idAluno);
		return optAluno.isPresent() ? optAluno.get() : new Aluno();
	}
	
  public void deletaAluno(long idAluno) {
  	ar.deleteById(idAluno);
  }
	
  public void saveAluno(Aluno a) {
  	ar.save(a);
  }
}
