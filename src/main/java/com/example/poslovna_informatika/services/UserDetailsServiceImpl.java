package com.example.poslovna_informatika.services;

import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.repositories.PreduzeceRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private PreduzeceRepository preduzeceRepository;

    public UserDetailsServiceImpl(PreduzeceRepository preduzeceRepository) {
        this.preduzeceRepository = preduzeceRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Preduzece preduzece = preduzeceRepository.findByEmail(email);

        if (email != null) {
            List<GrantedAuthority> authorities =
                    new ArrayList<>(Collections.singletonList(new SimpleGrantedAuthority(preduzece.getTip())));

            return new org.springframework.security.core.userdetails.User(preduzece.getEmail(),
                    preduzece.getPassword(), authorities);
        }

        throw new UsernameNotFoundException(email);
    }
}
