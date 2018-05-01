package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.StavkaCenovnika;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StavkaCenovnikaServiceInterface {

    List<StavkaCenovnika> findAll();

    StavkaCenovnika findOne(long id);

    Page<StavkaCenovnika> findAllByCenovnikId(long cenovnikId, Pageable pageable);

    List<StavkaCenovnika> findAllByRobaId(long robaId);

    List<StavkaCenovnika> findAllByCenaBetween(double pocetnaCena, double krajnjaCena);

    StavkaCenovnika save(StavkaCenovnika stavkaCenovnika);

    void remove(long id);

}
