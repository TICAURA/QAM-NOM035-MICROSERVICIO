package com.aura.nom.service;

import java.util.ArrayList;


import com.aura.nom.dao.AuthDao;
import com.aura.nom.dto.AuthDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private AuthDao authDao;

    //private PasswordD

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username != null){

            AuthDto authDto = authDao.getColaborador(username);

            authDto.setPassword(bcryptEncoder.encode(authDto.getPassword()));

            return new User(authDto.getEmail(), authDto.getPassword(),
                    new ArrayList<>());

        } else {
            logger.info("User not found with username: " + username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public AuthDto loadAuthDto(String username) throws UsernameNotFoundException {

        if (username != null){

            AuthDto authDto = authDao.getColaborador(username);

            authDto.setPassword(bcryptEncoder.encode(authDto.getPassword()));

            return authDto;

        } else {
            logger.info("User not found with username: " + username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
