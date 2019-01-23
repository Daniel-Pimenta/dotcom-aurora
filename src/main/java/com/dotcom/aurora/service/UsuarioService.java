package com.dotcom.aurora.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dotcom.aurora.security.Role;
import com.dotcom.aurora.security.RoleRepository;
import com.dotcom.aurora.security.User;
import com.dotcom.aurora.security.UserRepository;

@Service
public class UsuarioService {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private UserRepository ur;
	@Autowired
	private RoleRepository rr;
	
	private User user;
	private Role role;
	private List<Role> roles;
	private String msgErro = null;
	
	public User getUserLogedin() {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String userName = auth.getName();
    return ur.findByUsername(userName);
	}	
	
	public User createUsuario(User nu, long idRole) {
		msgErro = "";
		log.info("createUsuario("+nu.getUsername()+","+idRole+")");
	
		user = ur.findByUsername(nu.getUsername());
		
		if (user != null) {
			log.info("Email já cadastrado !!!");
			msgErro = "Este endereço de email ja consta no nosso cadastro," + (user.isEnable() ? " e foi confirmado" : " e aguarda confirmação !");
			log.info(msgErro);
			return user;
		}
		log.info("Cadastrando...");
		user = new User();
		user.setUserId(0);
		user.setUsername(nu.getUsername());
		user.setNome(nu.getNome());
		user.setSobrenome(nu.getSobrenome());
		user.setPassword( new BCryptPasswordEncoder().encode(nu.getPassword()) );
		
		long[] ids = {2};
		user.setRoles(rr.findAllById(ids));
		user.setEnable(false);
	  ur.save(user);
	  long userId = user.getUserId();
	  log.info("ID:"+userId);
	  return user;
	}
	
	public User confirmaUsuario(long id) {
		user = ur.findById(id).get();
		log.info("Confirmando criação de User:"+user.getUsername());
		user.setEnable(true);
		ur.save(user);
		return user;
	}

	public String getMsgErro() {
		return msgErro;
	}
	
	
}
