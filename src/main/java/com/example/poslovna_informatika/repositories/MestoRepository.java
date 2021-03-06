package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.Mesto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MestoRepository extends JpaRepository<Mesto, Long> {

    Page<Mesto> findAll(Pageable pageable);

    Mesto findByGrad(String grad);

    List<Mesto> findByDrzava(String drzava);

}
