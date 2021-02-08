package com.example.housemanager.controllers;

import com.example.housemanager.model.entities.Dweller;
import com.example.housemanager.model.entities.Flat;
import com.example.housemanager.services.DwellerService;
import com.example.housemanager.services.HtmlPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/dweller")
public class DwellerController {

    @Autowired
    DwellerService dwellerService;

    @Autowired
    HtmlPageService htmlPageService;

    @PostMapping("/create")
    public String createDweller(@RequestParam(value = "name") String name, @RequestParam(value = "lastName") String lastName, @RequestParam(value = "flatId") Long flatId) {
        try {
            this.dwellerService.createNewDweller(name, lastName, flatId);
            return htmlPageService.htmlCreated();
        } catch (Exception ex) {
            return htmlPageService.errorCreateDweller();
        }
    }

    @PostMapping("/delete-{id}")
    public String removeDweller(@RequestParam(value = "id") Long id) {
        try {
            this.dwellerService.deleteDweller(id);
            return htmlPageService.htmlDeleted();
        } catch (Exception ex) {
            return htmlPageService.errorDelete();
        }
    }

    @PostMapping("/{id}")
    public String dweller(@RequestParam(value = "id") Long id) {
        try {
            List<Dweller> dwellers = new ArrayList<>();
            dwellers.add(this.dwellerService.findOne(id));
            return htmlPageService.jsonDwellerListReturn(dwellers);
        } catch (Exception ex) {
            return htmlPageService.notFound();
        }
    }

    @GetMapping("/all")
    public String findAllDwellers() {
        return htmlPageService.jsonDwellerListReturn(this.dwellerService.findAll());
    }

    @PostMapping("/{name}{lastName}")
    public String dwellers(@RequestParam(value = "name") String name, @RequestParam(value = "lastName") String lastName){
        try {
            List<Dweller> dwellers = new ArrayList<>();
            dwellers.add(dwellerService.findByName(name, lastName));
            return htmlPageService.jsonDwellerListReturn(dwellers);
        } catch (Exception ex) {
            return htmlPageService.notFound();
        }
    }

    @PostMapping("/flat-paid-{id}")
    public String getDwellerFlat(@RequestParam(value = "id") Long id) {
        try {
            List<Flat> flats = new ArrayList<>();
            flats.add(this.dwellerService.findOne(id).getFlat());
            return htmlPageService.jsonFlatListReturn(flats);
        } catch (Exception ex) {
            return htmlPageService.notFound();
        }
    }

    @PostMapping("/add-email-{id}-{email}")
    public String editDwellerEmail(@RequestParam(value = "id") Long id, @RequestParam(value = "email") String email) {
        try {
            this.dwellerService.addEmail(email, id);
            return htmlPageService.htmlEdited();
        } catch (Exception ex) {
            return htmlPageService.errorEdit();
        }
    }


}
