import com.developersweb.controllers.UserController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class UserControllerTest {
    UserController userController;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RequestDispatcher requestDispatcher;
    @BeforeEach
    public void setUp()
    {
        userController=new UserController();
        MockitoAnnotations.initMocks(this);
    }
    @AfterEach
    public void end()
    {
        request=null;
        response=null;
        userController=null;
    }
    @Test
    public void doGet() throws ServletException, IOException {
        when(request.getParameter("parser")).thenReturn("DOM");
        when(request.getParameter("fileName")).thenReturn("developers");
        when(request.getRequestDispatcher("main.jsp")).thenReturn(requestDispatcher);
        userController.doGet(request, response);
        verify(request.getRequestDispatcher("main.jsp"));
    }
}
