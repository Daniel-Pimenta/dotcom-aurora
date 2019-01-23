package com.dotcom.aurora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dotcom.aurora.model.Escola;
import com.dotcom.aurora.model.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long>{

	@Query("select t from Turma t where t.escola = :escola")
	List<Turma> findAllByEscola(@Param("escola") Escola escola);
	
}
