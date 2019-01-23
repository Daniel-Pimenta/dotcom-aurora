package com.dotcom.aurora.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	UserDetails ud;
	
	@Autowired
	private UserRepository ur;
	
	private User usuario;
	private List<Role> roles;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername("+username+")");
		usuario = ur.findByUsername(username);
		/*
		if(usuario == null){
			log.info("Usuario não encontrado!");
			throw new UsernameNotFoundException("Usuario não encontrado !!!");
		}else {
			log.info("Usuario encontrado!");
		}
		if (usuario.getRoles() == null || usuario.getRoles().size()==0) {
			log.info("Não foram concedidas permições para este usuário !");
			throw new UsernameNotFoundException("Não foram concedidas permições para este usuário !!!");
		}else{
			log.info("Usuario  : "+usuario.getUsername());
			log.info("Qtd Roles: "+usuario.getRoles().size());
			roles = usuario.getRoles();
			for (int i=0; i<usuario.getRoles().size(); i++) {
				Role role = roles.get(i);
				log.info("Role:["+i+"] ->"+role.getRoleName());
			}
		}
		*/
		log.info("1");
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (Role role : usuario.getRoles()){
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
    }
    log.info("2");
		return new org.springframework.security.core.userdetails.User(
				usuario.getUsername(), 
				usuario.getPassword(), 
				//true, true, true, true, 
				grantedAuthorities
		);
	}

}
