package Controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Models.test_model_class;

//import com.google.gson.Gson;

import AD.Password_Change;

public class Dashboard_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

    Password_Change pc = new Password_Change();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	response.setContentType("text/plain");
	response.getWriter().write(pc.proba());
	}

}
