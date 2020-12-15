import com.developersweb.controllers.AuthFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AuthFilterTest {
    private AuthFilter userController;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private HttpSession session;
    @Mock
    RequestDispatcher requestDispatcher;
    @BeforeEach
    public void setUp() throws ServletException {
        userController = new AuthFilter();
        MockitoAnnotations.initMocks(this);
    }
    @AfterEach
    public void end()
    {
        userController=null;
    }
    @Test
    public void passingWrongCredentialsIsHandled() throws ServletException, IOException {
        when(request.getParameter("login")).thenReturn("vika.shakun@gmail.com");
        when(request.getParameter("password")).thenReturn("");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("register.jsp")).thenReturn(requestDispatcher);
        userController.doFilter(request, response, filterChain);
        verify(request.getRequestDispatcher("register.jsp"));
    }

}

