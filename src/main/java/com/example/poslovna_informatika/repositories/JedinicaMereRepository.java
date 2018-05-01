package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.JedinicaMere;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JedinicaMereRepository extends JpaRepository<JedinicaMere, Long> {

    Page<JedinicaMere> findAll(Pageable pageable);

    JedinicaMere findByNaziv(char[] naziv);

}
