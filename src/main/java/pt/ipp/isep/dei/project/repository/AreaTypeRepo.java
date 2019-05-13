package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.areatype.AreaType;

import java.util.List;
import java.util.Optional;

@Repository
public interface AreaTypeRepo extends CrudRepository<AreaType, Long> {

    List<AreaType> findAll();

    Optional<AreaType> findByName(String name);
}
