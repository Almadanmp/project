package pt.ipp.isep.dei.project.model.bridgeservices;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.repository.AreaTypeRepo;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;

public class GeographicAreaAreaTypeService {

    @Autowired
    private static GeographicAreaRepository geographicAreaRepository;

    @Autowired
    private static AreaTypeRepo areaTypeRepository;


}
