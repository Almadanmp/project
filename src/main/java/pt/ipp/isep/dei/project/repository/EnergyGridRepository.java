package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.EnergyGrid;

@Repository
public interface EnergyGridRepository extends CrudRepository<EnergyGrid, Long> {
}
