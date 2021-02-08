package com.example.housemanager.services;

import com.example.housemanager.model.entities.BlockOfFlats;
import com.example.housemanager.model.entities.Flat;
import com.example.housemanager.model.repositories.BlockOfFlatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlockOfFlatsService {

    @Autowired
    private BlockOfFlatsRepository blockOfFlatsRepository;

    @Autowired
    private FlatsService flatsService;

    public List<BlockOfFlats> findAll() {
        return blockOfFlatsRepository.findAll();
    }

    public void createNewBlockOfFlats(int blockNumber) {
         try {
             BlockOfFlats blockOfFlats = new BlockOfFlats();
             blockOfFlats.setBlockNumber(blockNumber);
             blockOfFlatsRepository.save(blockOfFlats);
         } catch(Exception ex) {
             System.out.println("Error in Creating new Block Of flats: " + ex);
         }
    }

    public BlockOfFlats findByNumber(int blockNumber) {
        List<BlockOfFlats> allBlockOfFlats = blockOfFlatsRepository.findAll();
        for(int i = allBlockOfFlats.size()-1; i >= 0; i--) {
            if(allBlockOfFlats.get(i).getBlockNumber() == blockNumber) {
                return allBlockOfFlats.get(i);
            }
        }
        return null;
    }

    public Optional<BlockOfFlats> findOne(Long id) {
        return blockOfFlatsRepository.findById(id);
    }

    public void deleteBlockOfFlats(Long id) {
        try {
            BlockOfFlats blockToDelete = blockOfFlatsRepository.findById(id).orElse(null);
            for(int i = blockToDelete.getFlats().size()-1; i >= 0; i--) {
                flatsService.deleteFlat(blockToDelete.getFlats().get(i).getId());
            }
            blockOfFlatsRepository.deleteById(id);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
