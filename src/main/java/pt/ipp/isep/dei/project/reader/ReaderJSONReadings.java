package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class ReaderJSONReadings implements Reader {

    @Override
    public JSONArray readFile(String filePath) {
        try {
            File file = new File(filePath);
            InputStream stream = new FileInputStream(file);
            JSONTokener tokener = new JSONTokener(stream);
            JSONObject object = new JSONObject(tokener);
            return getJSONArrayFromFile(object);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * This method receives a JSON object and returns the JSONArray
     * associated with the key "readings".
     *
     * @return JSONArray in case the key matches, throws a IllegalArgumentException
     * in case the key doesn't match.
     **/
    JSONArray getJSONArrayFromFile(JSONObject object) {
        try {
            return object.getJSONArray("readings");
        } catch (JSONException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
