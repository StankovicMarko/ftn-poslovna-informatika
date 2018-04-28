package com.example.poslovna_informatika;

import com.example.poslovna_informatika.model.Mesto;
import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.services.MestoService;
import com.example.poslovna_informatika.services.PreduzeceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PoslovnaInformatikaApplication implements CommandLineRunner {

    private PreduzeceService preduzeceRepository;
    private MestoService mestoService;

    public PoslovnaInformatikaApplication(PreduzeceService preduzeceRepository, MestoService mestoService) {
        this.preduzeceRepository = preduzeceRepository;
        this.mestoService = mestoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PoslovnaInformatikaApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... strings) throws Exception {
        Mesto mesto = new Mesto("NS", "Srbija");
        Preduzece preduzece = new Preduzece("Admin", "Admin adresa",
                0, "1337", "admin@email.com",
                bCryptPasswordEncoder().encode("admin"),
                "", mesto, "administrator");

        mestoService.save(mesto);
        preduzeceRepository.save(preduzece);
    }
}
