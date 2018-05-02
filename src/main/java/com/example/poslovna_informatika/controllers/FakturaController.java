package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.FakturaDTO;
import com.example.poslovna_informatika.services.FakturaService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/faktura")
public class FakturaController {

    private FakturaService fakturaService;
    private DataSource dataSource;

    @Autowired
    public FakturaController(FakturaService fakturaService, DataSource dataSource) {
        this.fakturaService = fakturaService;
        this.dataSource = dataSource;
    }

    @GetMapping(value = "/generate/{id}")
    public void generateFaktura(HttpServletResponse response, @PathVariable("id") long fakturaId) throws IOException, JRException, SQLException {
        InputStream is = new ClassPathResource("jasper_template/PI_faktura_izvestaj.jasper").getInputStream();

        Connection conn = dataSource.getConnection();

        Map<String, Object> params = new HashMap<>();
        params.put("FAKTURA_ID", fakturaId);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, params, conn);

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=faktura_" + fakturaId + ".pdf");

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }

    @GetMapping
    public ResponseEntity<List<FakturaDTO>> getAllFakture(Pageable pageable) {
        return new ResponseEntity<>(fakturaService.getFakture(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<FakturaDTO>> getFakture(@PathVariable("id") long id, Pageable pageable) {
        return new ResponseEntity<>(fakturaService.getFaktureByPreduzeceId(id, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/partner/{id}")
    public ResponseEntity<List<FakturaDTO>> getPartnerFakture(@PathVariable("id") long id) {
        return new ResponseEntity<>(fakturaService.getFaktureByPartnerId(id), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<FakturaDTO> saveItem(@RequestBody FakturaDTO fakturaDTO) {
        return new ResponseEntity<>(fakturaService.saveFakturaDTO(fakturaDTO), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<FakturaDTO> updateItem(@RequestBody FakturaDTO fakturaDTO, @PathVariable("id") long id) {
        FakturaDTO f = fakturaService.updateFakturaDTO(fakturaDTO, id);

        if (f == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(f, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (fakturaService.deleteFaktura(id)) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
