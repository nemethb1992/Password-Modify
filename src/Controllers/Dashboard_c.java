package Controllers;

import java.io.IOException;
//import java.io.OutputStream;
//import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.google.gson.Gson;
//
//import Models.test_model_class;

//import com.google.gson.Gson;

import AD.Password_Change;

public class Dashboard_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

    Password_Change pc = new Password_Change();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	List<String> CredList = new ArrayList<String>();
    	String usr ="", pwd_old="", pwd_new1="", pwd_new2="", respond = "Sikertelen Módosítás!";
    	
    usr = request.getParameter("usr");
    pwd_old = request.getParameter("pwd_old");
    pwd_new1 = request.getParameter("pwd_new1");
    pwd_new2 = request.getParameter("pwd_new2");
    
    CredList.add(usr);
    CredList.add(pwd_old);
    CredList.add(pwd_new1);
    CredList.add(pwd_new2);
    
	
//	response.setContentType("application/json");
//	new Gson().toJson(pc.proba(CredList), response.getWriter());
    if(pc.PassChange(CredList))
    {
    	respond = "Sikeres Módosítás!";
    }
	response.setContentType("text/plain");
	response.getWriter().write(respond);
	}

}
