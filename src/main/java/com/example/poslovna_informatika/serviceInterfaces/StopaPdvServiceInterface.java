package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.StavkaFakture;
import com.example.poslovna_informatika.model.StopaPDV;
import com.example.poslovna_informatika.services.StopaPdvService;

import java.util.Date;
import java.util.List;

public interface StopaPdvServiceInterface {

    List<StopaPDV> findAll();

    StopaPDV findOne(long id);

    List<StopaPDV> findAllByPdvId(long pdvId);

    List<StopaPDV> findAllByProcenat(double procenat);

    List<StopaPDV> findAllByDatumVazenja(Date datumVazenja);

    StopaPDV save(StopaPDV stopaPDV);

    void remove(long id);

}
