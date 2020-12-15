package com.developersweb.parsers;

public class ParserFactory {
    public IParser create(String serviceName) throws Exception {
        if(serviceName.equalsIgnoreCase("DOM"))
        {
            return new ReadDOM();
        }
        else if (serviceName.equalsIgnoreCase("SAX"))
        {
            return new ReadSAX();
        }

        throw new Exception("NotSupported");
    }
}
