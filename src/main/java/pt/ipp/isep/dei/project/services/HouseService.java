package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.repository.HouseRepository;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }


    public boolean saveHouse(House house) {
        House house1 = houseRepository.findByAddress(house.getAddress());
        if (house1 != null) {
            houseRepository.delete(house1);
        }
        houseRepository.save(house);
        return true;
    }
}

