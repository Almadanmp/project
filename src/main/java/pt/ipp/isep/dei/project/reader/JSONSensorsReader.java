package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JSONSensorsReader implements Reader {
    public JSONArray readFile(String filePath){
        try {
            File file = new File(filePath);
            InputStream stream = new FileInputStream(file);
            JSONTokener tokener = new JSONTokener(stream);
            JSONObject object = new JSONObject(tokener);
            return getElementArray(object);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    private JSONArray getElementArray(JSONObject fileObject){
        try {
            return fileObject.getJSONArray("sensor");
        } catch (JSONException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
