package phoenix.pmpassword.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Hashtable;
import java.util.List;
//
import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.*;

public class LdapPasswordModify {
	
	private final Charset UTF8_CHARSET = Charset.forName("UTF-8");
	private ModificationItem[] mods;

	String decodeUTF8(byte[] bytes) {
	    return new String(bytes, UTF8_CHARSET);
	}

	byte[] encodeUTF8(String string) {
	    return string.getBytes(UTF8_CHARSET);
	}
	
	
	
	public boolean updateUserPassword(String username, String password, String newPasswordParam)
	{
		Hashtable<String, String> env = new Hashtable<>(11);
		String ldapURL = "ldap://192.168.144.21:389/dc=pmhu,dc=local";
		String userName = "CN="+username+",DC=portal,DC=pmhu,DC=local";
		String newPassword = newPasswordParam;
		DirContext ctx = null;



		try
		{
			System.out.println("updating password...\n");
			String quotedPassword = "\"" + password + "\"";
			char unicodePwd[] = quotedPassword.toCharArray();
			byte pwdArray[] = new byte[unicodePwd.length * 2];
			for (int i = 0; i < unicodePwd.length; i++)
			{
				pwdArray[i * 2 + 1] = (byte) (unicodePwd[i] >>> 8);
				pwdArray[i * 2 + 0] = (byte) (unicodePwd[i] & 0xff);
			}
			System.out.print("encoded password: ");
			for (int i = 0; i < pwdArray.length; i++)
			{
				System.out.print(pwdArray[i] + " ");
			}
			
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "ldap://192.168.144.21:389/dc=pmhu,dc=local");
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL,  username + "@pmhu.local");
			env.put(Context.SECURITY_CREDENTIALS, password);

			InitialDirContext initialContext;
			try {
				initialContext = new InitialDirContext(env);
				ctx = initialContext;
			} catch (NamingException e1) {
				System.out.println("shit!");
			}
			if(ctx != null)
			{
				ModificationItem[] mods = new ModificationItem[1];
				mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("UnicodePwd", pwdArray));
				ctx.modifyAttributes("uid="+username+", dc=pmhu,dc=local", mods);
				System.out.println("siker!");
				return true;
			}

		}
		catch (Exception e)
		{
			System.out.println("update password error: " + e);
		}
		return false;
	}




	
	
	
	
	
//	 public boolean PassChange (List<String> list) {
//      	 
//
//         System.out.println("1. - pass!");
//	    	boolean preValid = false;
//	    	boolean changeValid = false;
//	        Hashtable<String, String> env = new Hashtable<>(11);
//	          String ldapURL = "LDAP://192.168.144.21:686";
//	          String userName = list.get(0) + "@pmhu.local";
//	          String oldPassword = list.get(1);
//	          String newPassword = list.get(2);
//	          
//	          //Access the keystore, this is where the Root CA public key cert was installed
//	          //Could also do this via command line java -Djavax.net.ssl.trustStore....
////	          String keystore = "/usr/java/jdk1.5.0_01/jre/lib/security/cacerts";
////	          System.setProperty("javax.net.ssl.trustStore",keystore);
//
//	          env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
//
//	          //set security credentials, note using simple cleartext authentication
//	          env.put(Context.SECURITY_AUTHENTICATION,"ssl");
//	          env.put(Context.SECURITY_PRINCIPAL,userName);
//	          env.put(Context.SECURITY_CREDENTIALS,oldPassword);
//	          env.put(Context.PROVIDER_URL,ldapURL);
////	          //specify use of ssl
////	          env.put(Context.SECURITY_PROTOCOL,"ssl");
//
//	          //connect to my domain controller
//	          
//	        preValid = Authentication(list.get(0),list.get(1));
//	        if(preValid)
//	        {
//
//	              System.out.println("2. - pass!");
//	          try {
//
//	               // Create the initial directory context
//	               LdapContext ctx = new InitialLdapContext(env,null);
//
//	               System.out.println("2. - pass!");
//	               System.out.println("3. - pass!");
//
//
//	               //Firstly delete the "unicdodePwd" attribute, using the old password
//	               //Then add the new password,Passwords must be both Unicode and a quoted string
//	               String oldQuotedPassword = "\"" + oldPassword + "\"";
//	               String newQuotedPassword = "\"" + newPassword + "\"";
//	          
//	               mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, new BasicAttribute("unicodePwd", encodeUTF8(oldQuotedPassword)));
//	               mods[1] = new ModificationItem(DirContext.ADD_ATTRIBUTE, new BasicAttribute("unicodePwd", encodeUTF8(newQuotedPassword)));
//
//	               System.out.println("5. - pass!");
//	               // Perform the update
//	               ctx.modifyAttributes("uid="+list.get(0)+",dc=pmhu,dc=local", mods);
//
//	               System.out.println("6. - pass!");
//	               System.out.println("Changed Password for: " + userName); 
//	               changeValid = true;
//	               ctx.close();
//
//	          } 
//	          catch (NamingException e) {
//	               System.err.println("Problem changing password: " + e);
//	          }
//	        }
//
//
//	      return changeValid;
//	   }

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
        
        //Access the keystore, this is where the Root CA public key cert was installed
        //Could also do this via command line java -Djavax.net.ssl.trustStore....
//        String keystore = "/usr/java/jdk1.5.0_01/jre/lib/security/cacerts";
//        System.setProperty("javax.net.ssl.trustStore",keystore);

//        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
//        env.put(Context.SECURITY_AUTHENTICATION,"simple");
//        env.put(Context.SECURITY_PRINCIPAL,adminName);
//        env.put(Context.SECURITY_CREDENTIALS,adminPassword);
//        env.put(Context.PROVIDER_URL,ldapURL);

//        //specify use of ssl
//        env.put(Context.SECURITY_PROTOCOL,"ssl");
                  
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
