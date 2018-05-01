package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.Cenovnik;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CenovnikRepository extends JpaRepository<Cenovnik, Long> {

    Page<Cenovnik> findAll(Pageable pageable);

    Page<Cenovnik> findAllByPreduzeceId(long preduzeceId, Pageable pageable);

    List<Cenovnik> findAllByDatumVazenjaBetween(Date pocetniDatum, Date kranjiDatum);

}
