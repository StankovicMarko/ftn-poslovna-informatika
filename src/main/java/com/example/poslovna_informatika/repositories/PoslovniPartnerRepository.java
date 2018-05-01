package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.PoslovniPartner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoslovniPartnerRepository extends JpaRepository<PoslovniPartner, Long> {

    Page<PoslovniPartner> findAll(Pageable pageable);

    PoslovniPartner findByNaziv(String naziv);

    List<PoslovniPartner> findAllByAdresa(String adresa);

    List<PoslovniPartner> findAllByVrsta(char[] vrsta);

    List<PoslovniPartner> findAllByMestoId(long mestoId);

    List<PoslovniPartner> findAllByPreduzeceId(long preduzeceId, Pageable pageable);

    List<PoslovniPartner> findAllByPreduzeceIdAndMestoId(long preduzeceId, long mestoId);
}
