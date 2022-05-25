package com.aura.nom.config.security;

import com.aura.nom.dto.AuthDto;
import com.aura.nom.exception.BusinessException;
import com.aura.nom.service.JwtUserDetailsService;
import com.aura.nom.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {


        try {

           // logger.info("[JWT Request Filter] START doFilterInternal.");

            String requestTokenHeader = request.getHeader("Authorization");

            String username = null;

            if (requestTokenHeader == null) {
                throw new BusinessException("[JWT Request Filter] NULL token.", HttpStatus.UNAUTHORIZED.value());
            }

            try {
                jwtTokenUtil.validateToken(requestTokenHeader);
                username = jwtTokenUtil.getUsernameFromToken(requestTokenHeader);
            } catch (IllegalArgumentException e) {
                throw new BusinessException("[JWT Request Filter] Unable to get JWT token. ", HttpStatus.UNAUTHORIZED.value());
            } catch (MalformedJwtException e) {
                throw new BusinessException("[JWT Request Filter] JWT malformed token.", HttpStatus.UNAUTHORIZED.value());
            } catch (ExpiredJwtException e){
                throw new BusinessException("[JWT Request Filter] Expired token.", HttpStatus.UNAUTHORIZED.value());
            }

            if (username == null) {
                throw new BusinessException("[JWT Request Filter] NULL username. ", HttpStatus.UNAUTHORIZED.value());
            }

            //logger.info("[JWT Request Filter] Getting USER");
            AuthDto authDto = jwtUserDetailsService.loadAuthDto(username);

            if (authDto == null) {
                throw new BusinessException("User not found with username: " + username, HttpStatus.UNAUTHORIZED.value());
            }

            // Once we get the token validate it.
            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                //Manually set security
                UserDetails userDetails = new User(authDto.getEmail(), authDto.getPassword(), new ArrayList<>());

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }


            request.setAttribute("idColaborador", authDto.getIdColaborador());
            response.setHeader("New-token", jwtTokenUtil.generateToken(username));
            response.setHeader("Access-Control-Expose-Headers","New-token");

            //logger.info("[JWT Request Filter] END doFilterInternal.");
            chain.doFilter(request, response);

        }catch (BusinessException e){
            logger.error(e.getError());
            ((HttpServletResponse) response).setHeader("Content-Type", "application/json");
            ((HttpServletResponse) response).setStatus(401);
            PrintWriter out = response.getWriter();
            out.print("{}");
            out.flush();

            //chain.doFilter(request, response);
        }
    }

}
