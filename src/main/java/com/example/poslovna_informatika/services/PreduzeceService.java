package com.example.poslovna_informatika.services;

import com.example.poslovna_informatika.dto.PreduzeceDTO;
import com.example.poslovna_informatika.model.Mesto;
import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.repositories.PreduzeceRepository;
import com.example.poslovna_informatika.serviceInterfaces.PreduzeceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreduzeceService implements PreduzeceServiceInterface {

    private PreduzeceRepository preduzeceRepository;
    private MestoService mestoService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PreduzeceService(PreduzeceRepository preduzeceRepository, MestoService mestoService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.preduzeceRepository = preduzeceRepository;
        this.mestoService = mestoService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<Preduzece> findAll() {
        return preduzeceRepository.findAll();
    }

    @Override
    public Preduzece findOne(long preduzeceId) {
        return preduzeceRepository.findOne(preduzeceId);
    }

    @Override
    public Preduzece findByNaziv(String naziv) {
        return preduzeceRepository.findByNaziv(naziv);
    }

    @Override
    public Preduzece findByPib(int pib) {
        return preduzeceRepository.findByPib(pib);
    }

    @Override
    public List<Preduzece> findAllByAdresa(String adresa) {
        return preduzeceRepository.findAllByAdresa(adresa);
    }

    @Override
    public List<Preduzece> findAllByTelefon(String telefon) {
        return preduzeceRepository.findAllByTelefon(telefon);
    }

    @Override
    public List<Preduzece> findAllByEmail(String email) {
        return preduzeceRepository.findAllByEmail(email);
    }

    @Override
    public List<Preduzece> findAllByMestoId(long mestoId) {
        return preduzeceRepository.findAllByMestoId(mestoId);
    }

    @Override
    public Preduzece save(Preduzece preduzece) {
        return preduzeceRepository.save(preduzece);
    }

    @Override
    public void remove(long id) {
        preduzeceRepository.delete(id);
    }

    public List<PreduzeceDTO> getPreduzeceByMesto(long id) {
        List<Preduzece> preduzeca = findAllByMestoId(id);
        List<PreduzeceDTO> preduzecaDTO = new ArrayList<>();
        for (Preduzece p : preduzeca) {
            preduzecaDTO.add(new PreduzeceDTO(p));
        }
        return preduzecaDTO;
    }

    public List<PreduzeceDTO> getAllPreduzeca() {
        List<Preduzece> preduzeca = findAll();
        List<PreduzeceDTO> preduzecaDTO = new ArrayList<>();
        for (Preduzece p : preduzeca) {
            preduzecaDTO.add(new PreduzeceDTO(p));
        }
        return preduzecaDTO;
    }

    public PreduzeceDTO savePreduzece(PreduzeceDTO preduzeceDTO) {
        Mesto mesto = mestoService.findOne(preduzeceDTO.getMestoId());

        String password = bCryptPasswordEncoder.encode(preduzeceDTO.getPassword());

        Preduzece p = new Preduzece(preduzeceDTO.getNaziv(), preduzeceDTO.getAdresa(),
                preduzeceDTO.getPib(), preduzeceDTO.getTelefon(), preduzeceDTO.getEmail(),
                password, preduzeceDTO.getLogoPath(), mesto, "other");
        p = save(p);

        return new PreduzeceDTO(p);
    }

    public PreduzeceDTO updatePreduzece(PreduzeceDTO preduzeceDTO, long id) {
        Preduzece p = findOne(id);

        if (p == null) {
            return null;
        }

        Mesto mesto = mestoService.findOne(preduzeceDTO.getMestoId());

        p.setNaziv(preduzeceDTO.getNaziv());
        p.setAdresa(preduzeceDTO.getAdresa());
        p.setPib(preduzeceDTO.getPib());
        p.setTelefon(preduzeceDTO.getTelefon());
        p.setEmail(preduzeceDTO.getEmail());
        p.setPassword(bCryptPasswordEncoder.encode(preduzeceDTO.getPassword()));
        p.setLogoPath(preduzeceDTO.getLogoPath());
        p.setMesto(mesto);

        p = save(p);

        return new PreduzeceDTO(p);
    }

    public boolean deletePreduzece(long id) {
        Preduzece p = findOne(id);
        if (p != null) {
            remove(id);
            return true;
        } else {
            return false;
        }
    }
}
