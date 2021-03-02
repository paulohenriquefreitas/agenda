package com.conexa.agenda.service;


import com.conexa.agenda.dto.UserRequestDTO;
import com.conexa.agenda.exception.CustomException;
import com.conexa.agenda.model.Medico;
import com.conexa.agenda.model.User;
import com.conexa.agenda.repository.MedicoRepository;
import com.conexa.agenda.repository.UserRepository;
import com.conexa.agenda.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MedicoRepository medicoRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  public String signin(UserRequestDTO userRequestDTO) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequestDTO.getUsername(), userRequestDTO.getPassword()));
      return jwtTokenProvider.createToken(userRequestDTO.getUsername(), userRepository.findByUsername(userRequestDTO.getUsername()).getRoles());
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String signup(User user) {
    if (!userRepository.existsByUsername(user.getUsername())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
      return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public void logoff(String token) {
    this.removerToken(token);
  }

  private void removerToken(String token) {
    Medico medico = medicoRepository.findByToken(token);

    if (medico == null) {
      throw new CustomException("Token não encontrado: " + token, HttpStatus.NOT_FOUND);
    }
    medico.setToken(null);
    medicoRepository.save(medico);
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public User search(String username) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;
  }

  public User whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public String refresh(String username) {
    return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
  }

}
