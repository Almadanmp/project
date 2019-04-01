package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;

import java.util.List;

@Repository
public interface TypeSensorRepository extends CrudRepository<TypeSensor, Long> {

    TypeSensor findByName(String name);

    List<TypeSensor> findAll();
}
