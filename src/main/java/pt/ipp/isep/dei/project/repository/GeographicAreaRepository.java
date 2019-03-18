package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.model.GeographicArea;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GeographicAreaRepository extends CrudRepository<GeographicArea, UUID>{
}
