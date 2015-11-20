package com.java.softsec.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
	public static void isEmailValid(String email)
	{
		boolean status =false;
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		
	}
	public static boolean validateFirstName( String firstName )
	{
		return firstName.matches( "[A-Z][a-zA-Z]*" );
	} // end method validateFirstName

   // validate last name
	public static boolean validateLastName( String lastName )
	{
		return lastName.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" );
	} //
	
	
	private static Pattern usrNamePtrn = Pattern.compile("^[a-z0-9_-]{6,14}$");
     
    public static boolean validateUserName(String userName){
         
        Matcher mtch = usrNamePtrn.matcher(userName);
        if(mtch.matches()){
            return true;
        }
        return false;
    }
    
    
    
    private static Pattern PASSWORD_PATTERN =    Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");

	

	  /**
	   * Validate password with regular expression
	   * @param password password for validation
	   * @return true valid password, false invalid password
	   */
	  public static boolean validatePassword( String password){
		  
		    String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}";
		    return password.matches(pattern);

	  }
	  
	  
	  /** isPhoneNumberValid: Validate phone number using Java reg ex. 
	  * This method checks if the input string is a valid phone number. 
	  * @param email String. Phone number to validate 
	  * @return boolean: true if phone number is valid, false otherwise. 
	  */  
	  public static boolean isPhoneNumberValid(String phoneNumber){  
		  boolean isValid = false;  
	  /* Phone Number formats: (nnn)nnn-nnnn; nnnnnnnnnn; nnn-nnn-nnnn 
	      ^\\(? : May start with an option "(" . 
	      (\\d{3}): Followed by 3 digits. 
	      \\)? : May have an optional ")"  
	      [- ]? : May have an optional "-" after the first 3 digits or after optional ) character.  
	      (\\d{3}) : Followed by 3 digits.  
	       [- ]? : May have another optional "-" after numeric digits. 
	       (\\d{4})$ : ends with four digits. 
	   
	           Examples: Matches following phone numbers: 
	           (123)456-7890, 123-456-7890, 1234567890, (123)-456-7890 
	   
	  */  
	  //Initialize reg ex for phone number.   
		  String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";  
		  CharSequence inputStr = phoneNumber;  
		  Pattern pattern = Pattern.compile(expression);  
		  Matcher matcher = pattern.matcher(inputStr);  
		  if(matcher.matches()){  
			  isValid = true;  
	  	}  
	  	return isValid;  
	  } 
	  
	  public static boolean validateAddress( String address )
	   {
	      return address.matches( 
	         "\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)" );
	   } 
	  
	  
	  
	  
	  
	  
}
