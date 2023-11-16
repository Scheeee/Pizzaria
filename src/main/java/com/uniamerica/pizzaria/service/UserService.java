package com.uniamerica.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      var userEntity = userRepository.findByUsername(username);
      System.out.println(userEntity.getUsername());
      return (UserDetails) userEntity;
    } catch (Exception e) {
      throw new UsernameNotFoundException("Usuário não localizado");
    }
  }
}
