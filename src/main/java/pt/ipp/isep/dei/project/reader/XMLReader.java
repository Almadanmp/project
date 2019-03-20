package pt.ipp.isep.dei.project.reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pt.ipp.isep.dei.project.model.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class XMLReader {

    /**
     * reads a XML file from a certain path and imports geographic areas and sensors from the file
     *
     * @param filePath path to the xml file
     * @param list     geographic area list to add the imported geographic areas
     */
    public int readFileXML(String filePath, GeographicAreaList list) {
        int result = 0;
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nListGeoArea = doc.getElementsByTagName("geographical_area");
            for (int i = 0; i < nListGeoArea.getLength(); i++) {
                if(list.addGeographicArea(getGeographicAreas(nListGeoArea.item(i)))){
                    result++;
                }
            }
            NodeList nListSensor = doc.getElementsByTagName("area_sensor");
            for (GeographicArea ga : list.getGeographicAreas()) {
                List<Sensor> sensorList = ga.getSensorList().getSensors();
                for (int j = 0; j < nListSensor.getLength(); j++) {
                    sensorList.add(getSensors(nListSensor.item(j)));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private GeographicArea getGeographicAreas(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        GeographicArea geoArea = new GeographicArea();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            geoArea.setDescription(getTagValue("description", element));
            geoArea.setId(getTagValue("id", element));
            geoArea.setLength(Double.parseDouble(getTagValue("length", element)));
            geoArea.setWidth(Double.parseDouble(getTagValue("width", element)));
            geoArea.setLocation(new Local(Double.parseDouble(getTagValue("latitude", element)),
                    Double.parseDouble(getTagValue("longitude", element)),
                    Double.parseDouble(getTagValue("altitude", element))));
            geoArea.setTypeArea(new TypeArea(getTagValue("type", element)));
            geoArea.setSensorList(new SensorList());
        }
        return geoArea;
    }

    private Sensor getSensors(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        Sensor sensor = new Sensor();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            sensor.setId(getTagValue("id", element));
            sensor.setName(getTagValue("name", element));
            String sensorDate = getTagValue("start_date", element);
            SimpleDateFormat validDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            try {
                date = validDateFormat.parse(sensorDate);
            } catch (ParseException c) {
            }
            sensor.setDateStartedFunctioning(date);
            sensor.setTypeSensor(new TypeSensor(getTagValue("type", element), getTagValue("units", element)));
            sensor.setActive();
            sensor.setLocal(new Local(Double.parseDouble(getTagValue("latitude", element)),
                    Double.parseDouble(getTagValue("longitude", element)),
                    Double.parseDouble(getTagValue("altitude", element))));
        }
        return sensor;
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
