package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.sensor.Reading;

import java.util.Date;
import java.util.List;

@Repository
public interface ReadingRepository extends CrudRepository<Reading, Long> {
    Reading findReadingByDateEqualsAndSensorId(Date date, String sensorID);

    List<Reading> findAll();

}
