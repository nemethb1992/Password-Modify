package phoenix.pmpassword.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import phoenix.pmpassword.util.LdapPasswordModify;

public class Change extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    LdapPasswordModify pc = new LdapPasswordModify();
    	List<String> CredList = new ArrayList<String>();
    	String usr ="", pwd_old="", pwd_new1="", pwd_new2="", respond = "Sikertelen Módosítás!";
    	
    usr = request.getParameter("usr");
    pwd_old = request.getParameter("pwd_old");
    pwd_new1 = request.getParameter("pwd_new1");
    pwd_new2 = request.getParameter("pwd_new2");
    
    
    if(pc.updateUserPassword(usr,pwd_old,pwd_new1))
    {
    	respond = "Sikeres Módosítás!";
    }
//    if(pc.Authentication(CredList.get(0), CredList.get(1)))
//    {
//    	respond = "Sikeres felismert!";
//    }
	response.setContentType("text/plain");
	response.getWriter().write(respond);
	}

}
