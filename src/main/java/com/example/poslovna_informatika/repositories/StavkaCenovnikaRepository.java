package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.StavkaCenovnika;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StavkaCenovnikaRepository extends JpaRepository<StavkaCenovnika, Long> {

    Page<StavkaCenovnika> findAllByCenovnikId(long cenovnikId, Pageable pageable);

    List<StavkaCenovnika> findAllByRobaId(long robaId);

    List<StavkaCenovnika> findAllByCenaBetween(double pocetnaCena, double krajnjaCena);

}
