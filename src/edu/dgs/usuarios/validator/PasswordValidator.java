package edu.dgs.usuarios.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

	// Patrón para validar password
	//private String regex =  "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*[@#$%]).{8,20})";
	private String regex =  "(" 			+ 
							"(?=.*[0-9])"	+ 
							"(?=.*[a-z])"	+ 
							"(?=.*[A-Z])"	+
							"(?!.*[@#$%])"	+
							"."				+ 
							"{8,16})";
	
	/*
	 * Description
	 * # Start of group
	 * 
	 * (?=.*\d)		    # must contains one digit from 0-9. equivalent to [0-9]
	 * (?=.*[a-z])		# must contains one lowercase characters
	 * (?=.*[A-Z])		# must contains one uppercase characters
	 * (?!.*[@#$%])		# must not contains one special symbols in the list "@#$%"
	 * .		        # match anything with previous condition checking
	 * {8,16}			# length at least 8 characters and maximum of 16	
	 * # End of group
	 *
	 * ?= means apply the assertion condition, meaningless by itself, always work with other combination
	 * Whole combination is means, 8 to 20 characters string with at least one digit, 
	 * one upper case letter, one lower case letter and not special symbol (“@#$%”).
	 * This regular expression pattern is very useful to implement a strong and complex password.
	 *  
	 */

	/*
	 * Minimo 8 caracteres
	 * Maximo 16
	 * Al menos una letra minucula
	 * Al menos una letra mayúscula
	 * Al menos un dígito
	 * No espacios en blanco
	 * No caracteres especial (Observar cambio ?= por ?!)
	 */

	 private String regex1 = "^"			 				+ 
			 				"(?=.*[a-z])"	 				+ 
			 				"(?=.*[A-Z])"	 				+
			 				"(?=.*\\d)"		 				+
			 				"(?!.*[$@$!%*?&])"				+
			 				"(?=\\S+$)"						+
			 				".{8,16}"						+ 
			 				"$";
	 
	 
	private Pattern pattern;
	private  Matcher matcher;
	
	public boolean verifyPassword(String password){
		pattern = Pattern.compile(regex1);
		matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public boolean verifyPasswordText(String password){
		    return validatePassword(password);
	}

	private static boolean validatePassword(final String pwd)    {
	    boolean result = false;
	    try {
	        if (pwd!=null) {
	            //------------------------------
	            //Parameteres
	            //------------------------------
	            final String MIN_LENGHT="8";
	            final String MAX_LENGHT="16";
	            final boolean SPECIAL_CHAR_NEEDED=false;

	            //------------------------------
	            //Modules
	            //------------------------------
	            //(?=.*[0-9]) a digit must occur at least once
	            final String ONE_DIGIT = "^(?=.*[0-9])";  

	            //(?=.*[a-z]) a lower case letter must occur at least once
	            final String LOWER_CASE = "(?=.*[a-z])";
	            
	            //(?=.*[A-Z]) an upper case letter must occur at least once
	            final String UPPER_CASE = "(?=.*[A-Z])";
	            
	            //(?=\\S+$) no whitespace allowed in the entire string
	            final String NO_SPACE = "(?=\\S+$)";
	            
	            //final String MIN_CHAR = ".{" + MIN_LENGHT + ",}";  
	            //.{8,} at least 8 characters
	            //.{5,10} represents minimum of 5 characters and maximum of 10 characters
	            final String MIN_MAX_CHAR = ".{" + MIN_LENGHT + "," + MAX_LENGHT + "}$";  
	            //final String MIN_MAX_CHAR = "([A-Za-z\\d]){" + MIN_LENGHT + "," + MAX_LENGHT + "}$";
	            
	            final String SPECIAL_CHAR;

            	if (SPECIAL_CHAR_NEEDED){
            		//(?=.*[@#$%^&+=]) a special character must occur at least once
            		SPECIAL_CHAR= "(?=.*[@#$%^&+=])"; 
	            }
	            else{
            		//(?!.*[@#$%^&+=]) a special character must occur at least once
            		SPECIAL_CHAR= "(?!.*[@#$%^&+=])"; 
	            }
            	
	            //------------------------------
	            //Pattern
	            //------------------------------
	            //String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
	            final String PATTERN = 	ONE_DIGIT + 
	            						LOWER_CASE + 
	            						UPPER_CASE + 
	            						SPECIAL_CHAR + 
	            						NO_SPACE + 
	            						MIN_MAX_CHAR;
	            System.out.println(PATTERN);
	            //------------------------------
	            result = pwd.matches(PATTERN);
	            //------------------------------
	            //_________________________
	        }    

	    } catch (Exception ex) {
	        result=false;
	    }

	    return result;
	}    	

	public boolean isPasswordValid(String password) {

	    String regExpn =
	    				"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.*[@#$%^&+=])(?=\\S+$).{8,}$";

	    CharSequence inputStr = password;

	    Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(inputStr);

	    if(matcher.matches())
	        return true;
	    else
	        return false;
	}
	
}
