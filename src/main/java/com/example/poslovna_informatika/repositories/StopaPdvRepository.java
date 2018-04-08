package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.StavkaFakture;
import com.example.poslovna_informatika.model.StopaPDV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StopaPdvRepository extends JpaRepository<StopaPDV, Long> {

    List<StopaPDV> findAllByPdvId(long pdvId);

    List<StopaPDV> findAllByProcenat(double procenat);

    List<StopaPDV> findAllByDatumVazenja(Date datumVazenja);



}
