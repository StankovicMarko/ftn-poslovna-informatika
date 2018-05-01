package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.GrupaRobe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GrupaRobeServiceInterface {

    Page<GrupaRobe> findAll(Pageable pageable);

    GrupaRobe findOne(long id);

    GrupaRobe findByNaziv(String naziv);

    Page<GrupaRobe> findAllByPreduzeceId(long preduzeceId, Pageable pageable);

    List<GrupaRobe> findAllByPdvId(long pdvId);

    GrupaRobe save(GrupaRobe grupaRobe);

    void remove(long id);

}
