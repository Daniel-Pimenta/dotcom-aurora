package com.dotcom.aurora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dotcom.aurora.model.Aluno;
import com.dotcom.aurora.model.Turma;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	List<Aluno> findAllByTurma(Turma turma);

}
