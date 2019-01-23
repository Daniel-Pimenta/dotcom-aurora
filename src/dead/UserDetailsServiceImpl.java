package com.dotcom.aurora.security;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dotcom.aurora.security.Role;
import com.dotcom.aurora.security.User;
import com.dotcom.aurora.security.UserRepository;

@Repository
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UsuarioRepository ur;
	private Usuario usuario;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("UserDetailsServiceImpl.loadUserByUsername("+username+")");
    usuario = ur.findByUsername(username);

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (Role role : usuario.getRoles()){
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
    }

    return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), grantedAuthorities);
	}

}
