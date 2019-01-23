package com.dotcom.aurora.security;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> { 
	
	@Query("select r from Role r where roleId in (:id)")
	List<Role> findAllById(@Param("id") long[] id);
	
}
