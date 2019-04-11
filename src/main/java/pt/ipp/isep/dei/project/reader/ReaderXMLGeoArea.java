package pt.ipp.isep.dei.project.reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pt.ipp.isep.dei.project.controller.ReaderController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.sensor.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReaderXMLGeoArea {

    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String ALTITUDE = "altitude";
    private static final String VALID_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * reads a XML file from a certain path and imports geographic areas and sensors from the file
     *
     * @param filePath path to the xml file
     * @param list     geographic area list to addWithoutPersisting the imported geographic areas
     */
    public int readFileXMLAndAddAreas(String filePath, GeographicAreaService list, AreaSensorService areaSensorService, ReadingService readingService, HouseService houseService, HouseSensorService houseSensorService) {
        ReaderController ctrl = new ReaderController(areaSensorService, readingService, houseService, houseSensorService);
        ReaderXML reader = new ReaderXML();
        Document doc = reader.readFile(filePath);
        doc.getDocumentElement().normalize();
        NodeList nListGeoArea = doc.getElementsByTagName("geographical_area");
        return ctrl.addGeoAreaNodeListToList(nListGeoArea, list, areaSensorService);
    }

    /**
     * Method to import a Geographic Area from a certain node
     *
     * @param node - node of the XML file
     * @return - Geographic Area that exists in the node
     */
    public boolean readGeographicAreasXML(Node node, GeographicAreaService list, AreaSensorService areaSensorService) {
        boolean result = false;
        GeographicArea geoArea;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String description = getTagValue("description", element);
            String id = getTagValue("id", element);
            double length = Double.parseDouble(getTagValue("length", element));
            double width = Double.parseDouble(getTagValue("width", element));
            Local local = new Local(Double.parseDouble(getTagValue(LATITUDE, element)),
                    Double.parseDouble(getTagValue(LONGITUDE, element)),
                    Double.parseDouble(getTagValue(ALTITUDE, element)));
            AreaType areaType = new AreaType(getTagValue("type", element));
            geoArea = new GeographicArea(id, areaType, length, width, local);
            geoArea.setDescription(description);
            NodeList nListSensor = element.getElementsByTagName("sensor");
            result = list.addAndPersistGA(geoArea);
            if (result) {
                for (int j = 0; j < nListSensor.getLength(); j++) {
                    areaSensorService.addWithPersist(readSensorsXML(nListSensor.item(j), geoArea));
                }
            }
        }
        return result;
    }

    /**
     * Method to import a Sensor from a certain node
     *
     * @param node - node of the XML file.
     * @return - Sensor that exists in the node
     */
    private AreaSensor readSensorsXML(Node node, GeographicArea geographicArea) {
        AreaSensor areaSensor = new AreaSensor();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String id = getTagValue("id", element);
            String name = getTagValue("name", element);
            String sensorDate = getTagValue("start_date", element);
            SensorType sensorType = new SensorType(getTagValue("type", element), getTagValue("units", element));
            SimpleDateFormat validDateFormat = new SimpleDateFormat(VALID_DATE_FORMAT);
            Local local = new Local(Double.parseDouble(getTagValue(LATITUDE, element)),
                    Double.parseDouble(getTagValue(LONGITUDE, element)),
                    Double.parseDouble(getTagValue(ALTITUDE, element)));
            Date date = new Date();
            Long gaID = geographicArea.getId();
            try {
                date = validDateFormat.parse(sensorDate);
            } catch (ParseException expected) {
                expected.getErrorOffset();
            }
            areaSensor = new AreaSensor(id, name, sensorType, local, date, gaID);
            areaSensor.setGeographicAreaId(geographicArea.getId());
        }
        return areaSensor;
    }

    /**
     * Gets the value of the tag correspondent to the String and the Element from the same Node
     *
     * @param tag     - String of the tag correspondent to the node
     * @param element - element correspondent to the nod
     * @return - returns the value in string
     */
    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

}
