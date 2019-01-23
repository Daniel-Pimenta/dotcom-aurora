package com.dotcom.aurora.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.username = :username")
	User findByUsername(@Param("username") String username);

	@Query("select u from User u where u.userId = :userId")
	User findByUserId(@Param("userId") long userId);
}
