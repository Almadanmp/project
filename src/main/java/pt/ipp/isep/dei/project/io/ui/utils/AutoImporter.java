package pt.ipp.isep.dei.project.io.ui.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;
import pt.ipp.isep.dei.project.repository.*;

@Service
public class AutoImporter {

    @Autowired
    private HouseCrudRepo houseCrudRepo;
    @Autowired
    private SensorTypeCrudRepo sensorTypeCrudRepo;
    @Autowired
    private AreaTypeCrudRepo areaTypeCrudRepo;
    @Autowired
    private GeographicAreaCrudRepo geographicAreaCrudRepo;
    @Autowired
    private EnergyGridCrudRepo energyGridCrudRepo;
    @Autowired
    private RoomCrudRepo roomCrudRepo;

    @Autowired
    GeographicAreaRepository geographicAreaRepository;
    @Autowired
    SensorTypeRepository sensorTypeRepository;
    @Autowired
    AreaTypeRepository areaTypeRepository;

    @Autowired
    InputHelperUI inputHelperUI;

    public void deleteDB() {
        houseCrudRepo.deleteAll();
        sensorTypeCrudRepo.deleteAll();
        areaTypeCrudRepo.deleteAll();
        geographicAreaCrudRepo.deleteAll();
        energyGridCrudRepo.deleteAll();
        roomCrudRepo.deleteAll();

    }

    public void importGA() {
        inputHelperUI.acceptPathJSONorXMLAndReadFile("C:\\Users\\andre\\IdeaProjects\\project_g2_v2\\src\\test\\resources\\geoAreaFiles\\DataSet_sprint07_GA.json", geographicAreaRepository, sensorTypeRepository, areaTypeRepository);
    }

    //To be continued

}
