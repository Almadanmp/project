package pt.ipp.isep.dei.project.model.bridgeservices;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.repository.AreaTypeCrudRepo;
import pt.ipp.isep.dei.project.repository.GeographicAreaCrudRepo;

public class GeographicAreaAreaTypeService {

    @Autowired
    private static GeographicAreaCrudRepo geographicAreaCrudRepo;

    @Autowired
    private static AreaTypeCrudRepo areaTypeCrudRepository;


}
