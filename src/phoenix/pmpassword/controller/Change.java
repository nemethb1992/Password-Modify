package phoenix.pmpassword.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import phoenix.pmpassword.util.ChangeUserPassword;
import phoenix.pmpassword.util.LdapPasswordModify;

public class Change extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	HttpSession session = request.getSession();

		ChangeUserPassword pc = new ChangeUserPassword();
    	String usr ="", pwd_old="", pwd_new1="", pwd_new2="", respond = "Sikertelen módosítás! --";
    	
    usr = request.getParameter("usr");
    pwd_old = request.getParameter("pwd_old");
    pwd_new1 = request.getParameter("pwd_new1");
    pwd_new2 = request.getParameter("pwd_new2");
    
    if(pwd_new1.equals(pwd_new2) && !pwd_old.equals(pwd_new1))
    {
        try {
			if(pc.PassChange(usr,pwd_old,pwd_new1,request))
			{
				respond = "Sikeres módosítás! --";
			}
		} catch (NamingException e) {
			respond += e;
		}
    }
    session = ChangeUserPassword.session;
    respond += "--" + (String)session.getAttribute("state");
//    if(pc.Authentication(CredList.get(0), CredList.get(1)))
//    {
//    	respond = "Sikeres felismert!";
//    }
	response.setContentType("text/plain");
	response.getWriter().write(respond);
	}

}
