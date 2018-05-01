package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.GrupaRobe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupaRobeRepository extends JpaRepository<GrupaRobe, Long> {

    GrupaRobe findByNaziv(String naziv);

    Page<GrupaRobe> findAll(Pageable pageable);

    Page<GrupaRobe> findAllByPreduzeceId(long preduzeceId, Pageable pageable);

    List<GrupaRobe> findAllByPdvId(long pdvId);

    Page<GrupaRobe> findAllByPreduzeceIdAndPdvId(long predId, long pdvId, Pageable pageable);
}
