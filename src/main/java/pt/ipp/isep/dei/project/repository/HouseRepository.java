package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.Address;
import pt.ipp.isep.dei.project.model.House;

@Repository
public interface HouseRepository extends CrudRepository<House, Long> {

    House findByAddress(Address address);

}
