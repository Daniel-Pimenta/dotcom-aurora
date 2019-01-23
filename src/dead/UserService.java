package com.dotcom.aurora.security;

import com.dotcom.aurora.security.User;

public interface UserService {
  void save(Usuario user);

  Usuario findByUsername(String username);
}
