package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;

import java.util.List;

@Repository
public interface HouseRepository extends CrudRepository<House, String> {

    House findByAddress(Address address);

    List<House> findAll();

}
