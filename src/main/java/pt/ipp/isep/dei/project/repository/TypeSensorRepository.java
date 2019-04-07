package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.util.List;

@Repository
public interface TypeSensorRepository extends CrudRepository<SensorType, Long> {

    SensorType findByName(String name);

    List<SensorType> findAll();
}
