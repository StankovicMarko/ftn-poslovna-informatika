package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.PDV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdvRepository extends JpaRepository<PDV, Long> {

    PDV findByNaziv(String naziv);

}
