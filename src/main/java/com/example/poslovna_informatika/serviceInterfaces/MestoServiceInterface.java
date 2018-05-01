package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.Mesto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MestoServiceInterface {

    Page<Mesto> findAll(Pageable pageable);

    Mesto findOne(long mestoId);

    Mesto findByGrad(String grad);

    List<Mesto> findByDrzava(String drzava);

    Mesto save(Mesto cenovnik);

    void remove(long id);

}
