package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;

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


}