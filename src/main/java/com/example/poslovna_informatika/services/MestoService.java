package com.example.poslovna_informatika.services;

import com.example.poslovna_informatika.dto.MestoDTO;
import com.example.poslovna_informatika.model.Mesto;
import com.example.poslovna_informatika.repositories.MestoRepository;
import com.example.poslovna_informatika.serviceInterfaces.MestoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MestoService implements MestoServiceInterface {

    private MestoRepository mestoRepository;

    @Autowired
    public MestoService(MestoRepository mestoRepository) {
        this.mestoRepository = mestoRepository;
    }

    @Override
    public List<Mesto> findAll() {
        return mestoRepository.findAll();

    }

    @Override
    public Mesto findOne(long mestoId) {
        return mestoRepository.findOne(mestoId);
    }

    @Override
    public Mesto findByGrad(String grad) {
        return mestoRepository.findByGrad(grad);
    }

    @Override
    public List<Mesto> findByDrzava(String drzava) {
        return mestoRepository.findByDrzava(drzava);
    }

    @Override
    public Mesto save(Mesto mesto) {
        return mestoRepository.save(mesto);
    }

    @Override
    public void remove(long id) {
        mestoRepository.delete(id);
    }


    public List<MestoDTO> getAllMesta() {
        List<Mesto> mesta = findAll();
        List<MestoDTO> mestoDTOS = new ArrayList<>();
        for (Mesto m : mesta) {
            mestoDTOS.add(new MestoDTO(m));
        }
        return mestoDTOS;
    }

    public MestoDTO updateMesto(MestoDTO mestoDTO, long id) {
        Mesto m = findOne(id);

        if (m == null) {
            return null;
        }
        m.setDrzava(mestoDTO.getDrzava());
        m.setGrad(mestoDTO.getGrad());

        m = save(m);
        return new MestoDTO(m);
    }

    public boolean deleteMesto(long id) {
        Mesto m = findOne(id);
        if (m != null) {
            remove(id);
            return true;
        }
        return false;
    }
}
