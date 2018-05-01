package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.Roba;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RobaRepository extends JpaRepository<Roba, Long> {

    Page<Roba> findAll(Pageable pageable);

    List<Roba> findAllByNaziv(String nazivRobe);

    List<Roba> findAllByGrupaRobeId(long grupaRobeId);

    Page<Roba> findAllByGrupaRobeId(long grupaRobeId, Pageable pageable);

    List<Roba> findAllByJedinicaMereId(long jedinicaMereId);

}
