package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.AreaType;

import java.util.List;

@Repository
public interface AreaTypeRepository extends CrudRepository<AreaType, Long> {

    List<AreaType> findAll();
}
