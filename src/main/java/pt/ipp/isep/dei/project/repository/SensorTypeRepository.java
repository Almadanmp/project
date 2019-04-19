package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.sensorType.SensorType;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorTypeRepository extends CrudRepository<SensorType, Long> {

    Optional<SensorType> findByName(String name);

    List<SensorType> findAll();
}
