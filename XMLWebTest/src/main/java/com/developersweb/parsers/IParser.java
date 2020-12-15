package com.developersweb.parsers;
import com.developersweb.entities.Developers;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface IParser {
    List<Developers> getDevelopersInfo() throws IOException, SAXException, ParserConfigurationException;
}
