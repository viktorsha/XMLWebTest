import com.developersweb.database.DatabaseHandler;
import com.developersweb.entities.Developers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
public class DatabaseHandlerTest {
    @Test
    public void updateEmptyDeveloperIsError()
    {
        final DatabaseHandler dbhandler = new DatabaseHandler();
        final String result = dbhandler.updateDeveloper(new Developers());
        MatcherAssert.assertThat(result, is("Error") );
    }
}
