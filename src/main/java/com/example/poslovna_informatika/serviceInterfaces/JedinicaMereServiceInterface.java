package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.JedinicaMere;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JedinicaMereServiceInterface {

    Page<JedinicaMere> findAll(Pageable pageable);

    JedinicaMere findOne(long id);

    JedinicaMere findByNaziv(char[] naziv);

    JedinicaMere save(JedinicaMere jedinicaMere);

    void remove(long id);

}
