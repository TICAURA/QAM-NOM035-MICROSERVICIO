package com.aura.nom.controller;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<String> exception(AccessDeniedException e) {
        return new ResponseEntity<String>("{\"error\":\"access denied\"}", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> exceptionUSERNOTFOUND(AccessDeniedException e) {
        return new ResponseEntity<String>("{\"error\":\"user not found\"}", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseBody
    public ResponseEntity<String> exceptionMALFORMJWT(AccessDeniedException e) {
        return new ResponseEntity<String>("{\"error\":\"invalid token\"}", HttpStatus.NOT_FOUND);
    }
}
