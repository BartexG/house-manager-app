package com.example.housemanager.services;

import com.example.housemanager.model.entities.Dweller;
import com.example.housemanager.model.entities.Flat;
import com.example.housemanager.model.repositories.DwellerRepository;
import com.example.housemanager.model.repositories.FlatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DwellerService {

    @Autowired
    private DwellerRepository dwellerRepository;

    @Autowired
    private FlatsService flatService;

    @Autowired
    private FlatsRepository flatsRepository;

    public void createNewDweller(String name, String lastName, Long flatId) {
        try {
            Dweller dweller = new Dweller();
            dweller.setName(name);
            dweller.setLastName(lastName);
            dweller.setEmail("");
            dwellerRepository.save(dweller);

            Flat flat = flatService.findOne(flatId).orElse(null);
            flat.setStatus("rented");
            dweller.setFlat(flat);
            List<Dweller> dwellers = flat.getDwellers();
            dwellers.add(dweller);
            flat.setDwellers(dwellers);
            dwellerRepository.save(dweller);
            flatsRepository.save(flat);
        } catch(Exception ex) {
            System.out.println("Error in Creating new Dweller: " + ex);
        }
    }

    public void deleteDweller(Long id) {
        try {
            dwellerRepository.deleteById(id);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Dweller findOne(Long id) {
        return dwellerRepository.findById(id).orElse(null);
    }

    public List<Dweller> findAll() {
        return dwellerRepository.findAll();
    }

    public Dweller findByName(String name, String lastName) {
        List<Dweller> dwellers = findAll();
        for(int i = dwellers.size()-1; i >= 0; i--) {
            if(dwellers.get(i).getName().equals(name) && dwellers.get(i).getLastName().equals(lastName)) {
                return dwellers.get(i);
            }
        }
        return null;
    }

    public void addEmail(String email, Long id) {
        Dweller dweller = this.findOne(id);
        dweller.setEmail(email);
        dwellerRepository.save(dweller);
    }

    public List<String> getEmails() {
        List<Dweller> dwellersList = this.getUnpaidDwellers();
        List<String> emails = new ArrayList<>();
        for(int i = dwellersList.size()-1; i >= 0; i--) {
            if(dwellersList.get(i).equals("") || dwellersList.get(i) == null || dwellersList.get(i).equals(" ")) {

            }
            else {
                emails.add(dwellersList.get(i).getEmail());
            }
        }
        return emails;
    }

    public List<Dweller> getUnpaidDwellers() {
        List<Flat> flatList = this.flatService.getUnpaidFlats();
        List<Dweller> dwellers = new ArrayList<>();
        for(int i = flatList.size()-1; i >= 0; i--) {
            List<Dweller> dwellersList = flatList.get(i).getDwellers();
            for(int j = dwellersList.size()-1; j >= 0; j--) {
                dwellers.add(dwellersList.get(j));
            }
        }
        return dwellers;
    }

    public Long getDwellerFlatID(Long id) {
        Dweller dweller = this.findOne(id);
        return dweller.getFlat().getId();
    }
}
