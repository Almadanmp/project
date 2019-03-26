package pt.ipp.isep.dei.project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.TypeArea;
import pt.ipp.isep.dei.project.repository.LocalRepository;
import pt.ipp.isep.dei.project.repository.TypeAreaRepository;

@Service
public class GeoAreaService {
    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private TypeAreaRepository typeAreaRepository;


    public void addGeoAreaLocal(GeographicArea geographicArea, Local local) {
        geographicArea.setLocation(local);
        localRepository.save(local);
    }

    public void setTypeArea(GeographicArea geographicArea, TypeArea typeArea) {
        geographicArea.setTypeArea(typeArea);
        typeAreaRepository.save(typeArea);
    }


}
