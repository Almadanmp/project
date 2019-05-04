package pt.ipp.isep.dei.project.io.ui.reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pt.ipp.isep.dei.project.controller.ReaderController;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaService;

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
    public int readFileXMLAndAddAreas(String filePath, GeographicAreaService list) {
        ReaderController ctrl = new ReaderController();
        ReaderXML reader = new ReaderXML();
        Document doc = reader.readFile(filePath);
        doc.getDocumentElement().normalize();
        NodeList nListGeoArea = doc.getElementsByTagName("geographical_area");
        return ctrl.addGeoAreaNodeListToList(nListGeoArea, list);
    }

    /**
     * Method to import a Geographic Area from a certain node
     *
     * @param node - node of the XML file
     * @return - Geographic Area that exists in the node
     */
    public boolean readGeographicAreasXML(Node node, GeographicAreaService geographicAreaService) {
        boolean result = false;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String description = getTagValue("description", element);
            String id = getTagValue("id", element);
            double length = Double.parseDouble(getTagValue("length", element));
            double width = Double.parseDouble(getTagValue("width", element));
            Local local = new Local(Double.parseDouble(getTagValue(LATITUDE, element)),
                    Double.parseDouble(getTagValue(LONGITUDE, element)),
                    Double.parseDouble(getTagValue(ALTITUDE, element)));
            String areaType = (getTagValue("type", element));
            try {
                GeographicArea areaObject = geographicAreaService.createGA(id, areaType, width, length, local);
                areaObject.setDescription(description);
                NodeList nListSensor = element.getElementsByTagName("sensor");
                result = geographicAreaService.addAndPersistGA(areaObject);
                if (result) {
                    for (int j = 0; j < nListSensor.getLength(); j++) {
                        readSensorsXML(nListSensor.item(j), areaObject,
                                geographicAreaService);
                        geographicAreaService.updateGeoArea(areaObject);
                    }
                }
            } catch (IllegalArgumentException ignored) {
            }

        }
        return result;
    }

    /**
     * Method to import a Sensor from a certain node
     *
     * @param node - node of the XML file.
     */
    private void readSensorsXML(Node node, GeographicArea geographicArea, GeographicAreaService geographicAreaService) {
        AreaSensor areaSensor;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String id = getTagValue("id", element);
            String name = getTagValue("name", element);
            String sensorDate = getTagValue("start_date", element);
            String sensorTypeName = getTagValue("type", element);
            String sensorTypeUnit = getTagValue("units", element);
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
            try {
                areaSensor = geographicAreaService.createAreaSensor(id, name, sensorTypeName, sensorTypeUnit, local, date, gaID);
                areaSensor.setGeographicAreaId(geographicArea.getId());
                geographicArea.addSensor(areaSensor);
            } catch (IllegalArgumentException ignored) {
            }
        }
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
