package AD;

//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.Hashtable;
import java.util.List;
//
//import javax.naming.*;
//import javax.naming.directory.*;
//import Models.test_model_class;

public class Password_Change {


	public  List<String> proba(List<String> list)
	{
		list.add("Helló a szerverrõl");
		return list;
	}
    public boolean PassChange (List<String> list) {
   	 
    	boolean valid = false;
//        Hashtable<String, String> env = new Hashtable<String, String>();   
//       String adminName = "adminuser";   
//       String adminPassword = "pass";   
//       String userName = "CN="+list.get(0)+"@pmhu.local";   
//       String oldPassword = list.get(1);   
//       String newPassword = list.get(2);   
//       String searchBase = "dc=com";   
//
//       env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
//       env.put(Context.PROVIDER_URL, "ldap://192.168.144.21:389");
////       env.put(Context.SECURITY_AUTHENTICATION, "simple");
//       env.put(Context.SECURITY_AUTHENTICATION, "DIGEST-MD5");
////       env.put(Context.SECURITY_PRINCIPAL, adminName);
////       env.put(Context.SECURITY_CREDENTIALS, adminPassword);
//       env.put(Context.SECURITY_PRINCIPAL, userName);
//       env.put(Context.SECURITY_CREDENTIALS, oldPassword);
//       
//       //Elképzelhetõ hogy ez a folyamat csak admin megadásával mûködik, így viszont bármikor.
//       //1# TODO Tesztelni a user saját hitelesítésével.
//
//       try {
//
//           // Create the initial directory context
//           DirContext ctx = new InitialDirContext(env);
//            
//            
//
//
//           // Create the search controls
//           SearchControls searchCtls = new SearchControls();
//
//           // Specify the search scope
//           searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//
//           // Specify the attributes to return
//           String returnedAtts[] = { "cn=Username Surname" };
//           searchCtls.setReturningAttributes(returnedAtts);
//
//           String searchFilter = "(&(objectClass=user)(objectClass=person)(sAMAccountName=Username))";
//           
//            
//
//            
//           // Specify the Base for the search
//
//           // initialize counter to total the results
//           int totalResults = 0;
//
//           // Search for objects using the filter
//           NamingEnumeration answer = ctx.search(searchBase, searchFilter,
//                   searchCtls);
//
//           //while (answer.hasMoreElements()) {
//           SearchResult sr = (SearchResult) answer.next();
//
//           totalResults++;
//
//           System.out.println(">>>" + sr.getName());
//
//          // }
//
//           // Replace the "unicdodePwd" attribute with a new value
//           // Password must be both Unicode and a quoted string
//           String newQuotedPassword = "\"" + newPassword + "\"";
//           byte[] newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");
//
//           // set password is a ldap modfy operation
//           ModificationItem[] mods = new ModificationItem[1];
//             mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
//                   new BasicAttribute("unicodePwd", newUnicodePassword));
//
//           if (mods == null) {
//               System.out.println("Mods is empty");
//
//           } else {
//               System.out.println(mods);
//           }
//
//           // Perform the update
//            ctx.modifyAttributes(userName, mods);
//  
//
//           // Check attributes
//
//
//           Attributes attrs = sr.getAttributes();
//           ctx.modifyAttributes(userName, DirContext.REPLACE_ATTRIBUTE, attrs);
//              
//
//           System.out.println("Reset Password for: " + userName);
//           valid = true;
//           ctx.close();
//
//       } catch (NamingException e) {
//           System.out.println("Problem resetting password: " + e);
//
//       } catch (UnsupportedEncodingException e) {
//           System.out.println("Problem encoding password: " + e);
//       }
       return valid;
   }
}
