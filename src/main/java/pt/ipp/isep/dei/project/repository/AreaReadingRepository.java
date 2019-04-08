package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.sensor.Reading;

@Repository
public interface AreaReadingRepository extends CrudRepository<Reading, Long> {
}
