package pt.ipp.isep.dei.project.repository;

import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.GeographicArea;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

@Repository
public interface GeographicAreaRepository extends CrudRepository<GeographicArea, UUID>{
}
