package phoenix.pmpassword.util;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;

import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPException;
import netscape.ldap.util.DN;

public class ChangeUserPassword {

	static HttpServletRequest request = null;
	public static HttpSession session;
	
	public boolean Authentication(String name, String pass) throws NamingException
	{
		boolean valid = false;

      Hashtable<String, String> env = new Hashtable<>(11);
      env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, "ldap://192.168.144.21:389");
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, name + "@pmhu.local");
    env.put(Context.SECURITY_CREDENTIALS, pass);
    
          if (!"".equals(name) && !"".equals(pass)) {
        	  DirContext ctx = new InitialDirContext(env);
              valid = true;
              ctx.close();
          }



		
		return valid;
	}

    public boolean PassChange (String username, String oldPassword, String newPassword, HttpServletRequest request) throws NamingException, UnsupportedEncodingException {

    	ChangeUserPassword.request = request; 
    	session = ChangeUserPassword.request.getSession();
        ex("Belépett - pass");
    	boolean preValid = false;
    	boolean changeValid = false;

      Hashtable<String, String> env = new Hashtable<>(11);
        String ldapURL = "ldap://192.168.144.21:636";
        String adminName = username + "@pmhu.local";
        String adminPassword = oldPassword;
        String usr = "uid="+username+",dc=pmhu,dc=local";
        String pwd = newPassword;

//        String keystore = "Valamilyen URL cert";
//        System.setProperty("javax.net.ssl.trustStore",keystore);

        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.SECURITY_AUTHENTICATION,"simple");
        env.put(Context.SECURITY_PROTOCOL,"ssl");
        env.put(Context.SECURITY_PRINCIPAL,adminName);
        env.put(Context.SECURITY_CREDENTIALS,adminPassword);
        env.put(Context.PROVIDER_URL,ldapURL);

        ex(" - pass");

//        //specify use of ssl
//        env.put(Context.SECURITY_PROTOCOL,"ssl");
                  
        //connect to my domain controller
      preValid = Authentication(username,oldPassword);
      if(preValid)
      {
          ex("autentikációs belépés - pass");
             // Create the initial directory context
    	  DirContext ctx = new InitialDirContext(env);
             ex("ldapContext létrehozása - pass");
       
             //set password is a ldap modfy operation
             ModificationItem[] mods = new ModificationItem[1];
             ex("mod létrehozása - pass");

             String newQuotedPassword = "\"" + pwd + "\"";
             byte[] newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");
             ex("jelszó kódolás - pass");

             mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("unicodePwd", newUnicodePassword));

             ex("mod hozzáadása - pass");
             // Perform the update
             ctx.modifyAttributes(usr, mods);

           changeValid = true;
             System.out.println("Reset Password for: " + usr);     
             ctx.close();


      }
      
      return changeValid;
   }
   
    public void ex(String text)
    {
  	  session.setAttribute("state", text);
    }
    
    
//    public boolean PassChange (List<String> list) {
//    	 
//    	boolean preValid = false;
//    	boolean changeValid = false;
//
//      Hashtable<String, String> env = new Hashtable<>(11);
//        String ldapURL = "ldap://192.168.144.21:389/dc=pmhu,dc=local";
//        String adminName = list.get(0) + "@pmhu.local";
//        String adminPassword = list.get(1);
//        String userName = "CN="+list.get(0)+",DC=portal,DC=pmhu,DC=local";
//        String newPassword = list.get(2);
//        
//        //Access the keystore, this is where the Root CA public key cert was installed
//        //Could also do this via command line java -Djavax.net.ssl.trustStore....
////        String keystore = "/usr/java/jdk1.5.0_01/jre/lib/security/cacerts";
////        System.setProperty("javax.net.ssl.trustStore",keystore);
//
//        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
//        env.put(Context.SECURITY_AUTHENTICATION,"simple");
//        env.put(Context.SECURITY_PRINCIPAL,adminName);
//        env.put(Context.SECURITY_CREDENTIALS,adminPassword);
//        env.put(Context.PROVIDER_URL,ldapURL);
//
////        //specify use of ssl
////        env.put(Context.SECURITY_PROTOCOL,"ssl");
//                  
//        //connect to my domain controller
//      preValid = Authentication(list.get(0),list.get(1));
//      if(preValid)
//      {
//        try {
//
//             // Create the initial directory context
//             LdapContext ctx = new InitialLdapContext(env, null);
//       
//             //set password is a ldap modfy operation
//             ModificationItem[] mods = new ModificationItem[1];
//
//             System.out.println("2. - OK!");
//             String newQuotedPassword = "\"" + newPassword + "\"";
//             byte[] newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");
//
//             mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("unicodePwd", newUnicodePassword));
//
//             System.out.println("3. - OK!");
//             // Perform the update
//             ctx.modifyAttributes(userName, mods);
//
//             System.out.println("4. - OK!");
//           changeValid = true;
//             System.out.println("Reset Password for: " + userName);     
//             ctx.close();
//
//        } 
//        catch (NamingException e) {
//             System.out.println("Problem resetting password: " + e);
//        }
//        catch (UnsupportedEncodingException e) {
//             System.out.println("Problem encoding password: " + e);
//        }
//      }
//      return changeValid;
//   }
}
	

