package com.developersweb.parsers;

import com.developersweb.entities.Developers;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadSAX implements IParser
{
    private static int counter = 0;
    public static List<Developers> parseSAX() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory fact =  SAXParserFactory.newInstance();
        SAXParser saxParser = fact.newSAXParser();
        List<Developers> developersDict = new ArrayList<>();
        final Developers[] developer = new Developers[1];
        DefaultHandler handler = new DefaultHandler()
        {
            boolean bname= false, bsurname=false, bage=false, bposition=false;
            public void startElement(String uri, String localName, String qName, Attributes attributes)
            {
                if(qName.equals("Name"))
                {
                    bname = true;
                }
                if(qName.equals("Surname"))
                {
                    bsurname=true;
                }
                if(qName.equals("Age"))
                {
                    bage=true;
                }
                if(qName.equals("Position"))
                {
                    bposition=true;
                }
            }
            public void endElement(String uri, String localName, String qName)
            {

            }
            public void characters(char[] ch, int start, int length)
            {
                if(bname)
                {
                    developer[0] = new Developers();
                    developer[0].setName(new String(ch, start, length));
                    bname=false;
                }
                if(bsurname)
                {
                    developer[0].setSurname(new String(ch, start, length));
                    bsurname=false;
                }
                if(bage)
                {
                    developer[0].setAge(new String(ch, start, length));
                    bage=false;
                }
                if(bposition)
                {
                    developer[0].setPosition(new String(ch, start, length));
                    developer[0].setId(String.valueOf(counter));
                    developersDict.add(developer[0]);
                    bposition=false;
                }
            }
        };
        saxParser.parse("H:\\Viktoria\\University\\3course\\SiTAiRIS\\XMLWebTest\\src\\main\\resources\\developers.xml",handler);
        return developersDict;
    }

    @Override
    public List<Developers> getDevelopersInfo() throws IOException, SAXException, ParserConfigurationException {
        return parseSAX();
    }
}
