package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.Cenovnik;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface CenovnikServiceInterface {

    Page<Cenovnik> findAll(Pageable pageable);

    Cenovnik findOne(long id);

    Page<Cenovnik> findAllByPreduzeceId(long preduzeceId, Pageable pageable);

    List<Cenovnik> findAllByDatumVazenjaBetween(Date pocetniDatum, Date kranjiDatum);

    Cenovnik save(Cenovnik cenovnik);

    void remove(long id);

}
