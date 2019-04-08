package pt.ipp.isep.dei.project.services.units;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface Unit {

    String buildString();

}
