package pt.ipp.isep.dei.project.reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.Sensor;

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
     * @param list geographic area list to add the imported geographic areas
     */
    public void readFileXML(String filePath, GeographicAreaList list) {
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nListGeoArea = doc.getElementsByTagName("tagNameForGeoArea");
            List<GeographicArea> empList = list.getGeographicAreas();
            for (int i = 0; i < nListGeoArea.getLength(); i++) {
                empList.add(getGeographicAreas(nListGeoArea.item(i)));
            }
            NodeList nListSensor = doc.getElementsByTagName("tagNameForSensor");
            for (GeographicArea ga : empList) {
                List<Sensor> sensorList = ga.getSensorList().getSensors();
                for (int j = 0; j < sensorList.size(); j++) {
                    sensorList.add(getSensors(nListSensor.item(j)));
                }
            }
            // just an example of how we can print attributes from XML file
//            for (int temp = 0; temp < nList.getLength(); temp++) {
//                Node nNode = nList.item(temp);
//                System.out.println("\nCurrent Element :" + nNode.getNodeName());
//
//                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//                    Element eElement = (Element) nNode;
//                    System.out.println("Student roll no : "
//                            + eElement.getAttribute("rollno"));
//                    System.out.println("First Name : "
//                            + eElement
//                            .getElementsByTagName("firstname")
//                            .item(0)
//                            .getTextContent());
//                    System.out.println("Last Name : "
//                            + eElement
//                            .getElementsByTagName("lastname")
//                            .item(0)
//                            .getTextContent());
//                    System.out.println("Nick Name : "
//                            + eElement
//                            .getElementsByTagName("nickname")
//                            .item(0)
//                            .getTextContent());
//                    System.out.println("Marks : "
//                            + eElement
//                            .getElementsByTagName("marks")
//                            .item(0)
//                            .getTextContent());
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private GeographicArea getGeographicAreas(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        GeographicArea geoArea = new GeographicArea();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            geoArea.setId(getTagValue("tagID", element)); //just an example, alter the tags when we have the correct
            // XML file
            geoArea.setDescription(getTagValue("tag", element));
            geoArea.setWidth(Double.parseDouble(getTagValue("tagWIDTH", element)));
            geoArea.setLength(Double.parseDouble(getTagValue("tagLENGTH", element)));
        }
        return geoArea;
    }

    private Sensor getSensors(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        Sensor sensor = new Sensor();

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            sensor.setId(getTagValue("tagID", element)); //just an example, alter the tags when we have the correct
            // XML file
            String sensorDate = getTagValue("tagSensorDate", element);
            SimpleDateFormat validDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            try {
                date = validDateFormat.parse(sensorDate);
            } catch (ParseException c) {
            }
            sensor.setDateStartedFunctioning(date);
            sensor.setName(getTagValue("tagName", element));
            sensor.setActive();
        }
        return sensor;
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
