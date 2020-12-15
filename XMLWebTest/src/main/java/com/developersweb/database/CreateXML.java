package com.developersweb.database;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CreateXML
{
    public static void createXML() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element root = doc.createElement("Developers");
        doc.appendChild(root);

        Element element = doc.createElement("Developer");
        root.appendChild(element);

        Attr attr = doc.createAttribute("Id");
        attr.setValue("1");
        element.setAttributeNode(attr);

        Element name = doc.createElement("Name");
        name.appendChild(doc.createTextNode("Igor"));
        element.appendChild(name);

        Element surname = doc.createElement("Surname");
        surname.appendChild(doc.createTextNode("Verushovich"));
        element.appendChild(surname);

        Element age = doc.createElement("Age");
        age.appendChild(doc.createTextNode("20"));
        element.appendChild(age);

        Element position = doc.createElement("Position");
        position.appendChild(doc.createTextNode("Junior Java developer"));
        element.appendChild(position);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        StreamResult streamResult = new StreamResult(new File("developers.xml"));
        transformer.transform(source, streamResult);

    }
}
