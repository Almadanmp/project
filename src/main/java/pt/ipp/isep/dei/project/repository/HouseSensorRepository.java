package pt.ipp.isep.dei.project.repository;

import org.apache.catalina.LifecycleState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.sensor.HouseSensor;

import java.util.List;

@Repository
public interface HouseSensorRepository extends CrudRepository<HouseSensor, String> {

    List<HouseSensor> findAll();
}
