package com.example.poslovna_informatika.services;


import com.example.poslovna_informatika.dto.PoslovnaGodinaDTO;
import com.example.poslovna_informatika.model.PoslovnaGodina;
import com.example.poslovna_informatika.repositories.PoslovnaGodinaRepository;
import com.example.poslovna_informatika.serviceInterfaces.PoslovnaGodinaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoslovnaGodinaService implements PoslovnaGodinaServiceInterface {

    private PoslovnaGodinaRepository poslovnaGodinaRepository;

    @Autowired
    public PoslovnaGodinaService(PoslovnaGodinaRepository poslovnaGodinaRepository) {
        this.poslovnaGodinaRepository = poslovnaGodinaRepository;
    }

    @Override
    public List<PoslovnaGodina> findAll() {
        return poslovnaGodinaRepository.findAll();

    }

    @Override
    public PoslovnaGodina findOne(long id) {
        return poslovnaGodinaRepository.findOne(id);
    }

    @Override
    public List<PoslovnaGodina> findAllByGodinaBetween(int pocetnaGodina, int krajnjaGodina) {
        return poslovnaGodinaRepository.findAllByGodinaBetween(pocetnaGodina, krajnjaGodina);
    }

    @Override
    public List<PoslovnaGodina> findAllByZakljucena(boolean zakljucena) {
        return poslovnaGodinaRepository.findAllByZakljucena(zakljucena);
    }

    @Override
    public PoslovnaGodina save(PoslovnaGodina poslovnaGodina) {
        return poslovnaGodinaRepository.save(poslovnaGodina);
    }

    @Override
    public void remove(long id) {
        poslovnaGodinaRepository.delete(id);
    }


    public List<PoslovnaGodinaDTO> getAllPoslovnaGodina() {
        List<PoslovnaGodina> poslovnaGodinas = findAll();
        List<PoslovnaGodinaDTO> poslovnaGodinaDTOS = new ArrayList<PoslovnaGodinaDTO>();
        for (PoslovnaGodina pg : poslovnaGodinas) {
            poslovnaGodinaDTOS.add(new PoslovnaGodinaDTO(pg));
        }

        return poslovnaGodinaDTOS;
    }

    public PoslovnaGodinaDTO savePoslovnaGodina(PoslovnaGodinaDTO poslovnaGodinaDTO) {
        PoslovnaGodina pg = new PoslovnaGodina(poslovnaGodinaDTO.getGodina(), poslovnaGodinaDTO.isZakljucena());
        return new PoslovnaGodinaDTO(save(pg));
    }

    public PoslovnaGodinaDTO updatePoslovnaGodina(PoslovnaGodinaDTO poslovnaGodinaDTO, long id) {
        PoslovnaGodina pg = findOne(id);

        if (pg == null) {
            return null;
        }
        pg.setGodina(poslovnaGodinaDTO.getGodina());
        pg.setZakljucena(poslovnaGodinaDTO.isZakljucena());

        return new PoslovnaGodinaDTO(save(pg));
    }

    public boolean deletePoslovnaGodina(long id) {
        PoslovnaGodina pg = findOne(id);
        if (pg != null) {
            remove(id);
            return true;
        }
        return false;
    }
}
