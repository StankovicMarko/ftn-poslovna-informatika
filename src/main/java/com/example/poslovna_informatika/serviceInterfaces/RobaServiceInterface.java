package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.Roba;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RobaServiceInterface {

    Page<Roba> findAll(Pageable pageable);

    Roba findOne(long id);

    List<Roba> findAllByNaziv(String nazivRobe);

    List<Roba> findAllByGrupaRobeId(long grupaRobeId);

    Page<Roba> findAllByGrupaRobeId(long grupaRobeId, Pageable pageable);

    List<Roba> findAllByJedinicaMereId(long jedinicaMereId);

    Roba save(Roba roba);

    void remove(long id);

}
