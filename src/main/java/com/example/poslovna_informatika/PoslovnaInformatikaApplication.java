package com.example.poslovna_informatika;

import com.example.poslovna_informatika.model.Mesto;
import com.example.poslovna_informatika.model.PDV;
import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.model.StopaPDV;
import com.example.poslovna_informatika.repositories.PreduzeceRepository;
import com.example.poslovna_informatika.services.MestoService;
import com.example.poslovna_informatika.services.PdvService;
import com.example.poslovna_informatika.services.StopaPdvService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PoslovnaInformatikaApplication implements CommandLineRunner {

    private PreduzeceRepository preduzeceRepository;
    private MestoService mestoService;
    private PdvService pdvService;
    private StopaPdvService stopaPdvService;

    public PoslovnaInformatikaApplication(PreduzeceRepository preduzeceRepository,
                                          MestoService mestoService,
                                          PdvService pdvService,
                                          StopaPdvService stopaPdvService) {
        this.preduzeceRepository = preduzeceRepository;
        this.mestoService = mestoService;
        this.pdvService = pdvService;
        this.stopaPdvService = stopaPdvService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PoslovnaInformatikaApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... strings) {
        PDV pdv = new PDV("Obican");
        StopaPDV stopaPDV = new StopaPDV(20, new Date(), pdv);
        Mesto mesto = new Mesto("NS", "Srbija");
        Preduzece preduzece = new Preduzece("Admin", "Admin adresa",
                0, "1337", "admin@email.com",
                bCryptPasswordEncoder().encode("admin"),
                "", mesto, "administrator");

        try {
            pdvService.save(pdv);
            stopaPdvService.save(stopaPDV);
            mestoService.save(mesto);
            preduzeceRepository.save(preduzece);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
