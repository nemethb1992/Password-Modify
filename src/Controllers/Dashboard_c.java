package Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AD.Password_Change;


@WebServlet("/Dashboard_c")
public class Dashboard_c extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Dashboard_c() {
        super();
    }
    Password_Change pc;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        response.getWriter().write(pc.proba());
	}

}
