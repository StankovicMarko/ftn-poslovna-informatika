package com.example.poslovna_informatika.services;


import com.example.poslovna_informatika.dto.StavkaCenovnikaDTO;
import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.Roba;
import com.example.poslovna_informatika.model.StavkaCenovnika;
import com.example.poslovna_informatika.repositories.StavkaCenovnikaRepository;
import com.example.poslovna_informatika.serviceInterfaces.StavkaCenovnikaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StavkaCenovnikaService implements StavkaCenovnikaServiceInterface {

    private StavkaCenovnikaRepository stavkaCenovnikaRepository;
    private RobaService robaService;
    private CenovnikService cenovnikService;

    @Autowired
    public StavkaCenovnikaService(StavkaCenovnikaRepository stavkaCenovnikaRepository, RobaService robaService, CenovnikService cenovnikService) {
        this.stavkaCenovnikaRepository = stavkaCenovnikaRepository;
        this.robaService = robaService;
        this.cenovnikService = cenovnikService;
    }

    @Override
    public List<StavkaCenovnika> findAll() {
        return stavkaCenovnikaRepository.findAll();

    }

    @Override
    public StavkaCenovnika findOne(long id) {
        return stavkaCenovnikaRepository.findOne(id);
    }

    @Override
    public Page<StavkaCenovnika> findAllByCenovnikId(long cenovnikId, Pageable pageable) {
        return stavkaCenovnikaRepository.findAllByCenovnikId(cenovnikId, pageable);
    }

    @Override
    public List<StavkaCenovnika> findAllByRobaId(long robaId) {
        return stavkaCenovnikaRepository.findAllByRobaId(robaId);
    }

    @Override
    public List<StavkaCenovnika> findAllByCenaBetween(double pocetnaCena, double krajnjaCena) {
        return stavkaCenovnikaRepository.findAllByCenaBetween(pocetnaCena, krajnjaCena);
    }


    @Override
    public StavkaCenovnika save(StavkaCenovnika stavkaCenovnika) {
        return stavkaCenovnikaRepository.save(stavkaCenovnika);
    }

    @Override
    public void remove(long id) {
        stavkaCenovnikaRepository.delete(id);
    }


    public List<StavkaCenovnikaDTO> getStavkeCenById(long id, Pageable pageable) {
        Page<StavkaCenovnika> stavkaCenovnikas = findAllByCenovnikId(id, pageable);
        List<StavkaCenovnikaDTO> stavkaCenovnikaDTOS = new ArrayList<>();
        for (StavkaCenovnika sc : stavkaCenovnikas) {
            stavkaCenovnikaDTOS.add(new StavkaCenovnikaDTO(sc));
        }
        return stavkaCenovnikaDTOS;
    }

    public List<StavkaCenovnikaDTO> getAllStavkeCen() {
        List<StavkaCenovnika> stavkaCenovnikas = findAll();
        List<StavkaCenovnikaDTO> stavkaCenovnikaDTOS = new ArrayList<>();
        for (StavkaCenovnika sc : stavkaCenovnikas) {
            stavkaCenovnikaDTOS.add(new StavkaCenovnikaDTO(sc));
        }
        return stavkaCenovnikaDTOS;
    }

    public StavkaCenovnikaDTO saveCenovnik(StavkaCenovnikaDTO stavkaCenovnikaDTO) {
        Cenovnik c = cenovnikService.findOne(stavkaCenovnikaDTO.getCenovnikId());
        Roba r = robaService.findOne(stavkaCenovnikaDTO.getRobaId());

        StavkaCenovnika sc = new StavkaCenovnika(stavkaCenovnikaDTO.getCena(), r, c);

        sc = save(sc);

        return new StavkaCenovnikaDTO(sc);
    }

    public StavkaCenovnikaDTO updateStavkaCen(StavkaCenovnikaDTO stavkaCenovnikaDTO, long id) {
        StavkaCenovnika sc = findOne(id);

        if (sc == null) {
            return null;
        }

        Cenovnik c = cenovnikService.findOne(stavkaCenovnikaDTO.getCenovnikId());
        Roba r = robaService.findOne(stavkaCenovnikaDTO.getRobaId());

        sc.setCena(stavkaCenovnikaDTO.getCena());
        sc.setCenovnik(c);
        sc.setRoba(r);

        sc = save(sc);

        return new StavkaCenovnikaDTO(sc);
    }

    public boolean deleteStavkaCen(long id) {
        StavkaCenovnika sc = findOne(id);
        if (sc != null) {
            remove(id);
            return true;
        }
        return false;
    }
}
