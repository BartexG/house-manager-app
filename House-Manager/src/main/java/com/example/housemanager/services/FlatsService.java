package com.example.housemanager.services;

import com.example.housemanager.model.entities.BlockOfFlats;
import com.example.housemanager.model.entities.Flat;
import com.example.housemanager.model.repositories.BlockOfFlatsRepository;
import com.example.housemanager.model.repositories.FlatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlatsService {

    @Autowired
    private FlatsRepository flatsRepository;

    @Autowired
    private BlockOfFlatsService blockOfFlatsService;

    @Autowired
    private DwellerService dwellerService;

    @Autowired
    private BlockOfFlatsRepository blockOfFlatsRepository;

    public List<Flat> findAll() {
        return flatsRepository.findAll();
    }

    public Optional<Flat> findOne(Long id) {
        return this.flatsRepository.findById(id);
    }

    public List<Flat> findFree() {
        List<Flat> allFlats = flatsRepository.findAll();
        List<Flat> freeFlats = new ArrayList<>();
        for(int i = allFlats.size()-1; i >= 0; i--) {
            if(allFlats.get(i).getStatus().equals("free")) {
                freeFlats.add(allFlats.get(i));
            }
        }
        return freeFlats;
    }

    public void createNewFlat(int number, int blockNumber) {
        try {
            Flat flat = new Flat();
            flat.setStatus("free");
            flat.setPayedForMonth(false);
            flat.setNumber(number);
            BlockOfFlats blockOfFlats = blockOfFlatsService.findByNumber(blockNumber);
            flat.setBlockOfFlats(blockOfFlats);
            flatsRepository.save(flat);

            List<Flat> flats = blockOfFlats.getFlats();
            flats.add(flat);
            blockOfFlats.setFlats(flats);
            flatsRepository.save(flat);
            blockOfFlatsRepository.save(blockOfFlats);
        } catch(Exception ex) {
            System.out.println("Error in Creating new Dweller: " + ex);
        }
    }

    public void deleteFlat(Long id) {
        try {
            Flat flatToDelete = flatsRepository.findById(id).orElse(null);
            for(int i = flatToDelete.getDwellers().size()-1; i >= 0; i--) {
                dwellerService.deleteDweller(flatToDelete.getDwellers().get(i).getId());
            }
            flatsRepository.deleteById(id);
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public Flat findByNumbers(int flatNumber, int blockNumber) {
        List<Flat> allFlats = flatsRepository.findAll();
        for(int i = allFlats.size()-1; i >= 0; i--) {
            if(allFlats.get(i).getNumber() == flatNumber) {
                return allFlats.get(i);
            }
        }
        return null;
    }

    public String checkFlatStatus(Long id) {
        Optional<Flat> flat = findOne(id);
        return flat.orElse(null).getStatus();
    }

    public void editStatus(Long id, String status) {
        Flat flat = flatsRepository.findById(id).orElse(null);
        flat.setStatus(status);
        flatsRepository.save(flat);
    }

    public void editPayStatus(Long id, boolean status) {
        Flat flat = flatsRepository.findById(id).orElse(null);
        flat.setPayedForMonth(status);
        flatsRepository.save(flat);
    }

    public List<Flat> getUnpaidFlats() {
        List<Flat> allFlats = flatsRepository.findAll();
        List<Flat> unpaidFlats = new ArrayList<>();
        for(int i = allFlats.size()-1; i >= 0; i--) {
            if(allFlats.get(i).getStatus().equals("rented")) {
                if(allFlats.get(i).isPayedForMonth() == false) {
                    unpaidFlats.add(allFlats.get(i));
                }
            }
        }
        return unpaidFlats;
    }

    public List<Flat> getUnpaidFlatsBlockOfFlats(int blockNumber) {
        BlockOfFlats blockOfFlats = blockOfFlatsService.findByNumber(blockNumber);
        List<Flat> allFlats = blockOfFlats.getFlats();
        List<Flat> unpaidFlats = new ArrayList<>();
        for(int i = allFlats.size()-1; i >= 0; i--) {
            if(allFlats.get(i).getStatus().equals("rented")) {
                if(allFlats.get(i).isPayedForMonth() == false) {
                    unpaidFlats.add(allFlats.get(i));
                }
            }
        }
        return unpaidFlats;
    }
}
