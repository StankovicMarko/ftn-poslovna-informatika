package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.Preduzece;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreduzeceRepository extends JpaRepository<Preduzece, Long> {

    Page<Preduzece> findAll(Pageable pageable);

    Preduzece findByNaziv(String naziv);

    Preduzece findByPib(int pib);

    Preduzece findByEmail(String email);

    List<Preduzece> findAllByAdresa(String adresa);

    List<Preduzece> findAllByTelefon(String telefon);

    List<Preduzece> findAllByEmail(String email);

    List<Preduzece> findAllByMestoId(long mestoId);

}
