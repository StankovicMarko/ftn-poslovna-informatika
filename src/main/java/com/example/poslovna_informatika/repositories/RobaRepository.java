package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.Roba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RobaRepository extends JpaRepository<Roba, Long> {

    List<Roba> findAllByNaziv(String nazivRobe);

    List<Roba> findAllByGrupaRobeId(long grupaRobeId);

    List<Roba> findAllByJediniceMereId(long jedinicaMereId);

}
