package com.example.poslovna_informatika.security;

import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.repositories.PreduzeceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final PreduzeceRepository preduzeceRepository;

    JwtAuthenticationFilter(AuthenticationManager authenticationManager, PreduzeceRepository preduzeceRepository) {
        this.authenticationManager = authenticationManager;
        this.preduzeceRepository = preduzeceRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            Preduzece creds = new ObjectMapper().readValue(request.getInputStream(), Preduzece.class);

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(),
                            new ArrayList<>());

            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) {

        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        SimpleGrantedAuthority sga = (SimpleGrantedAuthority) auth.getAuthorities().toArray()[0];
        String role = sga.getAuthority();

        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", role)
                .setExpiration(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, Constants.SECRET.getBytes())
                .compact();

        Preduzece preduzece = preduzeceRepository.findByEmail(userDetails.getUsername());

        response.addHeader(Constants.HEADER, Constants.TOKEN_PREFIX + token);
        response.addHeader("PredId", String.valueOf(preduzece.getId()));
        if (role.equals("administrator")) {
            response.addHeader("Location", "/preduzece.html");
        } else {
            response.addHeader("Location", "/fakture.html");
        }
    }
}
