package pt.ipp.isep.dei.project.reader;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class ReaderXML implements Reader {

    @Override
    public Document readFile(String filePath) {
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            return dBuilder.parse(inputFile);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
