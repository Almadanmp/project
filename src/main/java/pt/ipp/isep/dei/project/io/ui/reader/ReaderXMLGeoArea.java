package pt.ipp.isep.dei.project.io.ui.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pt.ipp.isep.dei.project.controller.controllercli.ReaderController;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReaderXMLGeoArea {
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String ALTITUDE = "altitude";
    private static final String VALID_DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    ReaderController readerController;

    /**
     * reads a XML file from a certain path and imports geographic areas and sensors from the file
     *
     * @param filePath path to the xml file
     * @param list     geographic area list to addWithoutPersisting the imported geographic areas
     */
    public int readFileXMLAndAddAreas(String filePath, GeographicAreaRepository list, AreaTypeRepository areaTypeRepository) {
        ReaderXML reader = new ReaderXML();
        Document doc = reader.readFile(filePath);
        doc.getDocumentElement().normalize();
        NodeList nListGeoArea = doc.getElementsByTagName("geographical_area");
        return readerController.addGeoAreaNodeListToList(nListGeoArea, list, areaTypeRepository);
    }

    /**
     * Method to import a Geographic Area from a certain node
     *
     * @param node - node of the XML file
     * @return - Geographic Area that exists in the node
     */
    public boolean readGeographicAreasXML(Node node, GeographicAreaRepository geographicAreaRepository, AreaTypeRepository areaTypeRepository) {
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
                AreaType areaType1 = areaTypeRepository.getAreaTypeByName(areaType);
                GeographicArea areaObject = geographicAreaRepository.createGA(id, areaType1.getName(), width, length, local);
                areaObject.setDescription(description);
                NodeList nListSensor = element.getElementsByTagName("sensor");
                result = geographicAreaRepository.addAndPersistGA(areaObject);
                if (result) {
                    for (int j = 0; j < nListSensor.getLength(); j++) {
                        readSensorsXML(nListSensor.item(j), areaObject);
                        geographicAreaRepository.updateGeoArea(areaObject);
                    }
                }
            } catch (IllegalArgumentException ok) {
                ok.getMessage();
            }

        }
        return result;
    }

    /**
     * Method to import a Sensor from a certain node
     *
     * @param node - node of the XML file.
     */
    private void readSensorsXML(Node node, GeographicArea geographicArea) {
        AreaSensor areaSensor;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String id = getTagValue("id", element);
            String name = getTagValue("name", element);
            String sensorDate = getTagValue("start_date", element);
            String sensorTypeName = getTagValue("type", element);
            SimpleDateFormat validDateFormat = new SimpleDateFormat(VALID_DATE_FORMAT);
            Local local = new Local(Double.parseDouble(getTagValue(LATITUDE, element)),
                    Double.parseDouble(getTagValue(LONGITUDE, element)),
                    Double.parseDouble(getTagValue(ALTITUDE, element)));
            Date date = new Date();
            try {
                date = validDateFormat.parse(sensorDate);
            } catch (ParseException expected) {
                expected.getErrorOffset();
            }
            try {
                areaSensor = geographicArea.createAreaSensor(id, name, sensorTypeName, local, date);
                geographicArea.addSensor(areaSensor);
            } catch (IllegalArgumentException ok) {
                ok.getMessage();
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
