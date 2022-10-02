package edu.dgs.usuarios.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

	// Patrón para validar el email
	private String regex =  "^" 					+
							"[_A-Za-z0-9-\\+]+" 	+ 
							"("						+
								"\\.[_A-Za-z0-9-]+" + 
							")*" 					+ 
							"@"						+
							"[A-Za-z0-9-]+"			+ 
							"("						+ 
								"\\.[A-Za-z0-9]+"	+
							")*"					+ 
							"("						+ 
								"\\.[A-Za-z]{2,}"	+
							")"						+
							"$";
	
	/*
	 * ^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$;
	 * 
	 * Description
	 * 
	 * The combination means: email address must start with “_A-Za-z0-9-\+” , 
	 * optional follow by “.[_A-Za-z0-9-]“, and end with a “@” symbol. 
	 * The email’s domain name must start with “A-Za-z0-9-”, 
	 * follow by first level Tld (.com, .net) “.[A-Za-z0-9]” 
	 * and optional follow by a second level Tld (.com.au, .com.my) “\.[A-Za-z]{2,}”, 
	 * where second level Tld must start with a dot “.” 
	 * and length must equal or more than 2 characters.
	 * 
	 * ^	start of the line
	 * [_A-Za-z0-9-\+]+	must start with string in the bracket [ ], must contains one or more (+)
	 * (	start of group #1
	 * \.[_A-Za-z0-9-]+	follow by a dot "." and string in the bracket [ ], must contains one or more (+)
	 * )*	end of group #1, this group is optional (*)
	 * @	must contains a "@" symbol
	 * [A-Za-z0-9-]+	follow by string in the bracket [ ], must contains one or more (+)
	 * ( start of group #2 - first level TLD checking
	 * \.[A-Za-z0-9]+	follow by a dot "." and string in the bracket [ ], must contains one or more (+)
	 * )*	end of group #2, this group is optional (*)
	 * (	start of group #3 - second level TLD checking
	 * \.[A-Za-z]{2,}	follow by a dot "." and string in the bracket [ ], with minimum length of 2
	 * )	end of group #3
	 * $	end of the line
	 */

	
	private String regexGoogle = "([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+";
	private Pattern pattern;
	private  Matcher mather;

	public boolean verifyMail(String email){

		pattern = Pattern.compile(regex);
		mather = pattern.matcher(email.trim());
		return mather.find();
	}
	
	
}
