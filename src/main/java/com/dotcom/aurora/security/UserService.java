package com.dotcom.aurora.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository ur;
	@Autowired
	private RoleRepository rr;
	
	private User usuario;
	private List<Role> roles;
	
}
