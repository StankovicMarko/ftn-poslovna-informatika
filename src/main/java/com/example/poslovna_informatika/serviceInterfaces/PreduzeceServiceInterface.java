package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.Preduzece;

import java.util.List;

public interface PreduzeceServiceInterface {

    List<Preduzece> findAll();

    Preduzece findOne(long preduzeceId);

    Preduzece findByNaziv(String naziv);

    Preduzece findByPib(int pib);

    List<Preduzece> findAllByAdresa(String adresa);

    List<Preduzece> findAllByTelefon(String telefon);

    List<Preduzece> findAllByEmail(String email);

    List<Preduzece> findAllByMestoId(long mestoId);

    Preduzece save(Preduzece preduzece);

    void remove(long id);

}
