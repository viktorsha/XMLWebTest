import com.developersweb.controllers.UserController;
import com.developersweb.entities.Developers;
import com.developersweb.parsers.IParser;
import com.developersweb.parsers.ParserFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class ReadDOMTest {
    IParser parser;
    ParserFactory parserFactory;
    @Before
    public void setUp() throws Exception {
        var serviceLocator = UserController.Compose();
        parserFactory = serviceLocator.resolve(ParserFactory.class);
        parser = parserFactory.create("DOM");
    }
    @After
    public void end()
    {
        parser=null;
        parserFactory=null;
    }
    @Test
    public void parseDOM() throws ParserConfigurationException, SAXException, IOException {
        List<Developers> developersInfo = parser.getDevelopersInfo();
        Assert.assertEquals(4, developersInfo.size());
    }

}
