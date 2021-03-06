package pt.ipp.isep.dei.project.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;

import java.util.List;

@Repository
public interface EnergyGridCrudRepo extends CrudRepository<EnergyGrid, String> {
    EnergyGrid findByName(String name);

    List<EnergyGrid> findAll();
}
