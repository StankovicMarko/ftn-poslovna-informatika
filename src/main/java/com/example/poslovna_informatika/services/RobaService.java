package com.example.poslovna_informatika.services;


import com.example.poslovna_informatika.dto.RobaDTO;
import com.example.poslovna_informatika.model.GrupaRobe;
import com.example.poslovna_informatika.model.JedinicaMere;
import com.example.poslovna_informatika.model.Roba;
import com.example.poslovna_informatika.repositories.RobaRepository;
import com.example.poslovna_informatika.serviceInterfaces.RobaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RobaService implements RobaServiceInterface {

    private RobaRepository robaRepository;
    private JedinicaMereService jedinicaMereService;
    private GrupaRobeService grupaRobeService;

    @Autowired
    public RobaService(RobaRepository robaRepository, JedinicaMereService jedinicaMereService,
                       GrupaRobeService grupaRobeService) {
        this.robaRepository = robaRepository;
        this.jedinicaMereService = jedinicaMereService;
        this.grupaRobeService = grupaRobeService;
    }

    @Override
    public Page<Roba> findAll(Pageable pageable) {
        return robaRepository.findAll(pageable);

    }

    @Override
    public Roba findOne(long id) {
        return robaRepository.findOne(id);
    }

    @Override
    public List<Roba> findAllByNaziv(String nazivRobe) {
        return robaRepository.findAllByNaziv(nazivRobe);
    }

    @Override
    public Page<Roba> findAllByGrupaRobeId(long grupaRobeId, Pageable pageable) {
        return robaRepository.findAllByGrupaRobeId(grupaRobeId, pageable);
    }

    @Override
    public List<Roba> findAllByGrupaRobeId(long grupaRobeId) {
        return robaRepository.findAllByGrupaRobeId(grupaRobeId);
    }

    @Override
    public List<Roba> findAllByJedinicaMereId(long jedinicaMereId) {
        return robaRepository.findAllByJedinicaMereId(jedinicaMereId);
    }


    @Override
    public Roba save(Roba roba) {
        return robaRepository.save(roba);
    }

    @Override
    public void remove(long id) {
        robaRepository.delete(id);
    }


    public List<RobaDTO> getAllItems(Pageable pageable) {
        Page<Roba> robas = findAll(pageable);
        List<RobaDTO> robaDTOS = new ArrayList<>();
        for (Roba r : robas) {
            robaDTOS.add(new RobaDTO(r));
        }
        return robaDTOS;
    }

    public List<RobaDTO> getAllItemsByPreduzeceId(long predId) {
        List<GrupaRobe> grupaRobes = grupaRobeService.findAllByPreduzeceId(predId);

        List<Roba> robas = new ArrayList<>();

        for (GrupaRobe gr : grupaRobes) {
            robas.addAll(findAllByGrupaRobeId(gr.getId()));
        }

        List<RobaDTO> robaDTOS = new ArrayList<>();
        for (Roba r : robas) {
            robaDTOS.add(new RobaDTO(r));
        }
        return robaDTOS;
    }

    public List<RobaDTO> getRobaByGrupa(long id, Pageable pageable) {
        Page<Roba> robas = findAllByGrupaRobeId(id, pageable);
        List<RobaDTO> robaDTOS = new ArrayList<>();
        for (Roba r : robas) {
            robaDTOS.add(new RobaDTO(r));
        }
        return robaDTOS;
    }

    public RobaDTO saveRoba(RobaDTO robaDTO) {
        JedinicaMere jm = jedinicaMereService.findOne(robaDTO.getJedinicaMereId());
        GrupaRobe gr = grupaRobeService.findOne(robaDTO.getGrupaRobeId());

        Roba r = new Roba(robaDTO.getNaziv(), jm, gr);

        r = save(r);
        return new RobaDTO(r);
    }

    public RobaDTO updateRoba(RobaDTO robaDTO, long id) {
        Roba r = findOne(id);

        if (r == null) {
            return null;
        }

        JedinicaMere jm = jedinicaMereService.findOne(robaDTO.getJedinicaMereId());
        GrupaRobe gr = grupaRobeService.findOne(robaDTO.getGrupaRobeId());

        r.setNaziv(robaDTO.getNaziv());
        r.setJedinicaMere(jm);
        r.setGrupaRobe(gr);

        r = save(r);

        return new RobaDTO(r);
    }

    public boolean deleteRoba(long id) {
        Roba r = findOne(id);
        if (r != null) {
            remove(id);
            return true;
        }
        return false;
    }
}
