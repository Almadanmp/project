package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import pt.ipp.isep.dei.project.controller.ReaderController;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ReaderJSONGeographicAreas implements Reader {


    public JSONArray readFile(String filePath) {
        try {
            File file = new File(filePath);
            InputStream stream = new FileInputStream(file);
            JSONTokener tokener = new JSONTokener(stream);
            JSONObject object = new JSONObject(tokener);
            JSONObject areaList = object.getJSONObject("geographical_area_list");
            return areaList.getJSONArray("geographical_area");
        } catch (FileNotFoundException e) {
            UtilsUI.printMessage("The file wasn't found.");
        }
        return new JSONArray();
    }

    /**
     * This method reads a .json file from its absolute filepath and returns an array of DTO objects formed
     * from the data in the file.
     *
     * @param filePath is the absolute filepath of the .json file in the system.
     * @param list     is the list of Geographic areas that comes from Main, since we still don't have a database, to which
     *                 we want to add the imported geographic areas.
     * @return is an array of data transfer geographic area objects created with the data in the .json file.
     */

    public int readFileAndAdd(String filePath, GeographicAreaList list) {
        ReaderController controller = new ReaderController();
        JSONArray geoAreas = readFile(filePath);
        GeographicArea[] geographicAreasArray;
        geographicAreasArray = controller.readGeoAreasJSON(geoAreas);
        return controller.addGeoAreasToList(geographicAreasArray, list);
    }

}