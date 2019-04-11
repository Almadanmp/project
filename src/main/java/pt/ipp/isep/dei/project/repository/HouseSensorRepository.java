package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.sensor.HouseSensor;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseSensorRepository extends CrudRepository<HouseSensor, String> {

    List<HouseSensor> findAll();

    List<HouseSensor> findAllById(String id);

    List<HouseSensor> findAllByRoomId(String name);

    Optional<HouseSensor> findByName(String name);

}
