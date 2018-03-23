package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.Cenovnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CenovnikRepository extends JpaRepository<Cenovnik, Long> {

    List<Cenovnik> findAllByPreduzeceId(long preduzeceId);

    List<Cenovnik> findAllByDatumVazenjaBetween(Date pocetniDatum, Date kranjiDatum);

}
