package AD;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
//
import javax.naming.*;
import javax.naming.directory.*;
import Models.test_model_class;
import java.util.Hashtable;
import javax.naming.*;
import javax.naming.ldap.*;
import javax.naming.directory.*;
import java.io.*;

public class Password_Change {
	
	
	public boolean Authentication(String name, String pass)
	{
		boolean valid = false;

      Hashtable<String, String> env = new Hashtable<>(11);
      env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, "ldap://192.168.144.21:389");
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, name + "@pmhu.local");
    env.put(Context.SECURITY_CREDENTIALS, pass);
    
      try {
          if (!"".equals(name) && !"".equals(pass)) {
        	  DirContext ctx = new InitialDirContext(env);
              valid = true;
              System.out.println("1. - pass!");
              ctx.close();
          }

      } catch (NamingException e) {
    	  valid = false;
      }
		
		return valid;
	}
	
	 public boolean PassChange (List<String> list) {
      	 
	    	boolean preValid = false;
	    	boolean changeValid = false;
	        Hashtable<String, String> env = new Hashtable<>(11);
	          String ldapURL = "ldap://192.168.144.21:389";
	          String userName = list.get(0) + "@pmhu.local";
	          String oldPassword = list.get(1);
	          String newPassword = list.get(2);
	          
	          //Access the keystore, this is where the Root CA public key cert was installed
	          //Could also do this via command line java -Djavax.net.ssl.trustStore....
//	          String keystore = "/usr/java/jdk1.5.0_01/jre/lib/security/cacerts";
//	          System.setProperty("javax.net.ssl.trustStore",keystore);

	          env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");

	          //set security credentials, note using simple cleartext authentication
	          env.put(Context.SECURITY_AUTHENTICATION,"simple");
	          env.put(Context.SECURITY_PRINCIPAL,userName);
	          env.put(Context.SECURITY_CREDENTIALS,oldPassword);
	          env.put(Context.PROVIDER_URL,ldapURL);
//	          //specify use of ssl
//	          env.put(Context.SECURITY_PROTOCOL,"ssl");

	          //connect to my domain controller
	          
	        preValid = Authentication(list.get(0),list.get(1));
	        if(preValid)
	        {
	          try {

	               // Create the initial directory context
	               LdapContext ctx = new InitialLdapContext(env,null);

	               System.out.println("2. - pass!");
	               //change password is a single ldap modify operation
	               //that deletes the old password and adds the new password
	               ModificationItem[] mods = new ModificationItem[2];
	          

	               //Firstly delete the "unicdodePwd" attribute, using the old password
	               //Then add the new password,Passwords must be both Unicode and a quoted string
	               String oldQuotedPassword = "\"" + oldPassword + "\"";
	               byte[] oldUnicodePassword = oldQuotedPassword.getBytes("UTF-16LE");
	               String newQuotedPassword = "\"" + newPassword + "\"";
	               byte[] newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");
	          
	               mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, new BasicAttribute("unicodePwd", oldUnicodePassword));
	               mods[1] = new ModificationItem(DirContext.ADD_ATTRIBUTE, new BasicAttribute("unicodePwd", newUnicodePassword));

	               System.out.println("3. - pass!");
	               // Perform the update
	               ctx.modifyAttributes("uid="+list.get(0)+",dc=pmhu,dc=local", mods);

	               System.out.println("4. - pass!");
	               System.out.println("Changed Password for: " + userName); 
	               changeValid = true;
	               ctx.close();

	          } 
	          catch (NamingException e) {
	               System.err.println("Problem changing password: " + e);
	          }
	          catch (UnsupportedEncodingException e) {
	               System.err.println("Problem encoding password: " + e);
	          }
	        }


	      return changeValid;
	   }

//    public boolean PassChange (List<String> list) {
//
//        Hashtable<String, String> env = new Hashtable<>(11);
//    	DirContext ctx;
//    	boolean preValid = false;
//    	boolean changeValid = false;
//    	
//    	
//
//      env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
//      env.put(Context.PROVIDER_URL, "ldap://192.168.144.21:389");
//      env.put(Context.SECURITY_AUTHENTICATION, "simple");
//      env.put(Context.SECURITY_PRINCIPAL, list.get(0) + "@pmhu.local");
//      env.put(Context.SECURITY_CREDENTIALS, list.get(1));
//
//      preValid = Authentication(list,env);
//      if(preValid)
//      {
//        try
//        {
//            // Create the initial directory context
//        	InitialDirContext initialContext = new InitialDirContext(env);
//            ctx = (DirContext)initialContext;
//            
//            System.out.println("2. - OK!");
//
//            ModificationItem[] mods = new ModificationItem[1];
//
//            Attribute mod0 = new BasicAttribute("userPassword", list.get(2));
//
//            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod0);
//
//            String theUserDN="CN="+list.get(0)+", DC=portal,DC=pmhu,DC=local";
//            ctx.modifyAttributes(theUserDN, mods);
//
//            System.out.println("3. - OK!");
//            
//            changeValid = true;
//        }
//        catch(Exception e)
//        {
//            System.err.println(e);
//        }
//      }
//
//      return changeValid;
//   }
	
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
