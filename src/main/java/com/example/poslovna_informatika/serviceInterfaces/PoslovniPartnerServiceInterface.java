package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.PoslovnaGodina;
import com.example.poslovna_informatika.model.PoslovniPartner;

import java.util.List;

public interface PoslovniPartnerServiceInterface {

    List<PoslovniPartner> findAll();

    PoslovniPartner findByNaziv(String naziv);

    List<PoslovniPartner> findAllByAdresa(String adresa);

    List<PoslovniPartner> findAllByVrsta(char[] vrsta);

    List<PoslovniPartner> findAllByMestoId(long mestoId);

    List<PoslovniPartner> findAllByPreduzeceId(long preduzeceId);

    PoslovniPartner save(PoslovniPartner poslovniPartner);

    void remove(long id);

}
