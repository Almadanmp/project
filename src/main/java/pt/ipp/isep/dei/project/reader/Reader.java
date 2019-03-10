package pt.ipp.isep.dei.project.reader;

import pt.ipp.isep.dei.project.model.GeographicAreaList;

/**
 * Represents all the objects that have the function of reading a file.
 */

public interface Reader {

    void readAndSet(GeographicAreaList geographicAreaList);

}
