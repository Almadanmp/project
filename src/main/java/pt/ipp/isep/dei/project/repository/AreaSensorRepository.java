package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;

import java.util.List;

@Repository
public interface AreaSensorRepository extends CrudRepository<AreaSensor, String> {

    List<AreaSensor> findAll();

    AreaSensor findByName(String name);

    List<AreaSensor> findByGeographicAreaId(Long id);
}