package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.PoslovnaGodina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoslovnaGodinaRepository extends JpaRepository<PoslovnaGodina, Long> {

    List<PoslovnaGodina> findAllByGodinaBetween(int pocetnaGodina, int krajnjaGodina);

    List<PoslovnaGodina> findAllByZakljucena(boolean zakljucena);

}
