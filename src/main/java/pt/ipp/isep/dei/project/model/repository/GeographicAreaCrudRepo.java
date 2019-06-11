package pt.ipp.isep.dei.project.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;

import java.util.List;

@Repository
public interface GeographicAreaCrudRepo extends CrudRepository<GeographicArea, Long> {
    List<GeographicArea> findAll();

    List<GeographicArea> findAllByAreaTypeID(String areaTypeID);

}
