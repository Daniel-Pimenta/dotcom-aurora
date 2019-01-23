package com.dotcom.aurora.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dotcom.aurora.security.User;
import com.dotcom.aurora.security.RoleRepository;
import com.dotcom.aurora.security.UserRepository;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsuarioRepository ur;
    @Autowired
    private RoleRepository rr;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Usuario usuario) {
    	usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
    	usuario.setRoles(rr.findAll());
      ur.save(usuario);
    }

    @Override
    public Usuario findByUsername(String username) {
        return ur.findByUsername(username);
    }
}
