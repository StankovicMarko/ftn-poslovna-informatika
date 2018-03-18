package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.GrupaRobe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupaRobeRepository extends JpaRepository<GrupaRobe, Long> {

    GrupaRobe findByNaziv(String naziv);

    List<GrupaRobe> findAllByPreduzeceId(long preduzeceId);

    List<GrupaRobe> findAllByPdvId(long pdvId);

}
