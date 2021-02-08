package com.example.housemanager.controllers;

import com.example.housemanager.model.entities.BlockOfFlats;
import com.example.housemanager.services.BlockOfFlatsService;
import com.example.housemanager.services.HtmlPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/block-of-flats")
public class BlockOfFlatsController {

    @Autowired
    private BlockOfFlatsService blockOfFlatsService;

    @Autowired
    private HtmlPageService htmlPageService;

    @Autowired
    private HomePageController homePageController;

    @GetMapping("/all")
    public String blockOfFlatsList() {
        return htmlPageService.jsonBlockOfFlatsListReturn(blockOfFlatsService.findAll());
    }

    @PostMapping("/create")
    public String createBlockOfFlats(@RequestParam(value = "blockNumber") int blockNumber) {
        this.blockOfFlatsService.createNewBlockOfFlats(blockNumber);
        return htmlPageService.htmlCreated();
    }

    @PostMapping("/{id}")
    public String blockOfFlats(@RequestParam(value = "id") Long id) {
        try {
            List<BlockOfFlats> blockOfFlatsList = new ArrayList<>();
            blockOfFlatsList.add(blockOfFlatsService.findOne(id).orElse(null));
            return htmlPageService.jsonBlockOfFlatsListReturn(blockOfFlatsList);
        } catch (Exception ex) {
            return htmlPageService.notFound();
        }
    }

    @PostMapping("/delete-{id}")
    public String deleteBlockOfFlats(@RequestParam(value = "id") Long id) {
        try {
            this.blockOfFlatsService.deleteBlockOfFlats(id);
            return htmlPageService.htmlDeleted();
        } catch (Exception ex) {
            return htmlPageService.errorDelete();
        }
    }

    @PostMapping("/{blockNumber}")
    public String blockOfFlats2(@RequestParam(value = "blockNumber") int blockNumber) {
        try {
            List<BlockOfFlats> blockOfFlatsList = new ArrayList<>();
            blockOfFlatsList.add(blockOfFlatsService.findByNumber(blockNumber));
            return htmlPageService.jsonBlockOfFlatsListReturn(blockOfFlatsList);
        } catch (Exception ex) {
            return htmlPageService.notFound();
        }
    }
}

