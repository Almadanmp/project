package pt.ipp.isep.dei.project.model.bridgeservices;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.repository.AreaTypeCrudeRepo;
import pt.ipp.isep.dei.project.repository.GeographicAreaCrudeRepo;

public class GeographicAreaAreaTypeService {

    @Autowired
    private static GeographicAreaCrudeRepo geographicAreaCrudeRepo;

    @Autowired
    private static AreaTypeCrudeRepo areaTypeCrudeRepository;


}
