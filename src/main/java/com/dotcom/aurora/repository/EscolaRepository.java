package com.dotcom.aurora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dotcom.aurora.model.Escola;

public interface EscolaRepository extends JpaRepository<Escola, Long> {
	
	
	@Query("select e from Escola e where e.idGestor = (select u.id from User u where u.username = :username) ")
	List<Escola> findAllByGestor(@Param("username") String username);

}
