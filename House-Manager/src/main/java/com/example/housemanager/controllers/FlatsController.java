package com.example.housemanager.controllers;

import com.example.housemanager.model.entities.Flat;
import com.example.housemanager.services.FlatsService;
import com.example.housemanager.services.HtmlPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/flats")
public class FlatsController {

    @Autowired
    private FlatsService flatsService;

    @Autowired
    private HtmlPageService htmlPageService;

    @GetMapping("/all")
    public String flatsList() {
        return htmlPageService.jsonFlatListReturn(flatsService.findAll());
    }

    @PostMapping("/{id}")
    public String flat(@RequestParam(value = "id") Long id) {
        try {
            List<Flat> flat = new ArrayList<>();
            flat.add(flatsService.findOne(id).orElse(null));
            return htmlPageService.jsonFlatListReturn(flat);
        } catch (Exception ex) {
            return htmlPageService.notFound();
        }
    }

    @GetMapping("/all-free")
    public String freeFlatsList() {
        return htmlPageService.jsonFlatListReturn(flatsService.findFree());
    }

    @PostMapping("/create")
    public String createFlat(@RequestParam(value = "number") int number, @RequestParam(value = "blockNumber") int blockNumber) {
        try {
            this.flatsService.createNewFlat(number, blockNumber);
            return htmlPageService.htmlCreated();
        } catch (Exception ex) {
            return htmlPageService.errorCreateFlat();
        }
    }

    @PostMapping("/delete-{id}")
    public String deleteBlockOfFlats(@RequestParam(value = "id") Long id) {
        try {
            this.flatsService.deleteFlat(id);
            return htmlPageService.htmlDeleted();
        } catch (Exception ex) {
            return htmlPageService.errorDelete();
        }
    }

    @GetMapping("/status-{id}")
    public String checkFlatStatus(@RequestParam(value = "id") Long id) {
        try {
            return this.flatsService.checkFlatStatus(id);
        } catch (Exception ex) {
            return htmlPageService.notFound();
        }
    }

    @PostMapping("/{blockNumber}{flatNumber}")
    public String findByNumbers(@RequestParam(value = "blockNumber") int blockNumber, @RequestParam(value = "flatNumber") int flatNumber) {
        List<Flat> flat = new ArrayList<>();
        try {
            flat.add(this.flatsService.findByNumbers(flatNumber, blockNumber));
            return htmlPageService.jsonFlatListReturn(flat);
        } catch (Exception ex) {
            return htmlPageService.notFound();
        }
    }

    @PostMapping("/edit-{id}-{status}")
    public String editFlatStatus(@RequestParam(value = "status") String status, @RequestParam(value = "id") Long id) {
        try {
            if(status.equals("free") || status.equals("rented")) {
                this.flatsService.editStatus(id, status);
                return htmlPageService.htmlEdited();
            }
            else {
                return htmlPageService.errorEdit();
            }
        } catch (Exception ex) {
            return htmlPageService.errorEdit();
        }
    }

    @GetMapping("/unpaid")
    public String getUnpaidFlats() {
        try {
            return htmlPageService.jsonFlatListReturn(flatsService.getUnpaidFlats());
        } catch (Exception ex) {
            return htmlPageService.notFound();
        }
    }

    @PostMapping("/unpaid-{blockNumber}")
    public String getUnpaidFlatsFromBlockOfFlats(@RequestParam(value = "blockNumber") int blockNumber) {
        try {
            return htmlPageService.jsonFlatListReturn(flatsService.getUnpaidFlatsBlockOfFlats(blockNumber));
        } catch (Exception ex) {
            return htmlPageService.notFound();
        }
    }

    @PostMapping("/paystatus-{id}-{payStatus}")
    public String editFlatPayStatus(@RequestParam(value = "id") Long id, @RequestParam(value = "payStatus") boolean payStatus) {
        try {
            this.flatsService.editPayStatus(id, payStatus);
            return htmlPageService.htmlEdited();
        } catch (Exception ex) {
            return htmlPageService.errorEdit();
        }
    }
}
