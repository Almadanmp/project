package pt.ipp.isep.dei.project.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;

import java.util.List;

@Repository
public interface HouseCrudRepo extends CrudRepository<House, String> {

    House findByAddress(Address address);

    List<House> findAll();

}
