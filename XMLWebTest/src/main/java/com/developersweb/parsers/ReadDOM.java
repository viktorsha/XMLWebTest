package com.developersweb.parsers;

import com.developersweb.entities.Developers;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadDOM implements IParser {

    public static List<Developers> parseDOM() throws ParserConfigurationException, IOException, SAXException {
        File xmlDoc = new File("H:\\Viktoria\\University\\3course\\SiTAiRIS\\XMLWebTest\\src\\main\\resources\\developers.xml");
        DocumentBuilderFactory docFact = DocumentBuilderFactory.newInstance();
        int counter = 0;
        List<Developers> devList = new ArrayList<>();
        DocumentBuilder docBuilder = docFact.newDocumentBuilder();
        Document doc = docBuilder.parse(xmlDoc);
        Developers developer;
        NodeList nList = doc.getElementsByTagName("Developer");
        for (int i=0; i<nList.getLength(); i++)
        {
            Node node = nList.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                counter+=1;
                developer = new Developers();
                developer.setId(String.valueOf(counter));
                developer.setName(element.getElementsByTagName("Name").item(0).getTextContent());
                developer.setSurname(element.getElementsByTagName("Surname").item(0).getTextContent());
                developer.setAge(element.getElementsByTagName("Age").item(0).getTextContent());
                developer.setPosition(element.getElementsByTagName("Position").item(0).getTextContent());
                devList.add(developer);

            }
        }
        return devList;
    }

    @Override
    public List<Developers> getDevelopersInfo() throws IOException, SAXException, ParserConfigurationException {
        return parseDOM();
    }
}
