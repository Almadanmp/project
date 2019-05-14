package pt.ipp.isep.dei.project.controllerWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.repository.GeographicAreaCrudeRepo;

import java.util.List;

@RestController
@RequestMapping("/gridSettings")
public class EnergyGridSettingsWebController {

    @Autowired
    private GeographicAreaCrudeRepo gridRepo;

    @GetMapping(value = "/grids")
    public @ResponseBody List<GeographicArea> test(){
        return gridRepo.findAll();
    }
}
