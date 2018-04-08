package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.PreduzeceDTO;
import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.serviceInterfaces.PreduzeceServiceInterface;
import com.example.poslovna_informatika.services.PreduzeceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Example of controller
 * Required services to be completed
 */
@Controller
public class PreduzeceController {

    private PreduzeceService preduzeceService;


    @GetMapping
    public ResponseEntity<List<PreduzeceDTO>> getPreduzeca() {
        List<Preduzece> preduzeca = preduzeceService.findAll();
        List<PreduzeceDTO> preduzecaDTO = new ArrayList<PreduzeceDTO>();
        for (Preduzece p : preduzeca) {
            preduzecaDTO.add(new PreduzeceDTO(p));
        }
        return new ResponseEntity<List<PreduzeceDTO>>(preduzecaDTO, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<AuctionDTO> saveItem(@RequestBody AuctionDTO auctionDTO, @RequestHeader(value = "Authorization") String token) {
        Auction a = new Auction();
        a.setStartDate(auctionDTO.getStartDate());
        a.setEndDate(auctionDTO.getEndDate());
        a.setStartPrice(auctionDTO.getStartPrice());
        a.setAuctionItem(itemService.findOne(auctionDTO.getItem_id()));
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.findByUsername(username);
        a.setUser(user);
        a = auctionService.save(a);
        logger.info("new auction" + a.getId() + a.getUser().getUsername());
        ;

        return new ResponseEntity<AuctionDTO>(new AuctionDTO(a), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<AuctionDTO> updateItem(@RequestBody AuctionDTO auctionDTO, @PathVariable("id") Integer id) {
        //a product must exist
        Auction auction = auctionService.findOne(id);

        if (auction == null) {
            return new ResponseEntity<AuctionDTO>(HttpStatus.BAD_REQUEST);
        }

        auction.setStartDate(auctionDTO.getStartDate());
        auction.setEndDate(auctionDTO.getEndDate());
        auction.setStartPrice(auctionDTO.getStartPrice());

        auction = auctionService.save(auction);

        logger.info("changed auction" + auction.getId() + auction.getUser().getUsername());

        return new ResponseEntity<AuctionDTO>(new AuctionDTO(auction), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id) {
        Auction auction = auctionService.findOne(id);
        if (auction != null) {
            auctionService.remove(id);
            logger.info("deleted auction" + auction.getId() + auction.getUser().getUsername());
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
