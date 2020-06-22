    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import java.io.Serializable;
   import java.util.regex.Matcher;
   import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;


   public class Validator implements Serializable{ 

 
   private final String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";      
   private final String cardNumberPattern = "\\d{4}-?\\d{4}-?\\d{4}-?\\d{4}"; //4-4-4-4 (hypens optional)
   private final String expiryPattern = "(0[1-9]|10|11|12)/20[0-9]{2}$"; // mm/yyyy
   private final String phoneNumberPattern = "^\\({0,1}((0|\\+61)(2|4|3|7|8)){0,1}\\){0,1}(\\ |-){0,1}[0-9]{2}(\\ |-){0,1}[0-9]{2}(\\ |-){0,1}[0-9]{1}(\\ |-){0,1}[0-9]{3}$"; 
    // Accepts all forms of Australian phone numbers in different formats (area code in brackets, no area code, spaces between 2-3 and 6-7th digits, +61 international dialing code).
   private final String postcodePattern = "/^[0-9]{4}$/"; // 4-digit postcode

   public Validator(){}       


   public boolean validate(String pattern, String input){       
      Pattern regEx = Pattern.compile(pattern);       
      Matcher match = regEx.matcher(input);       
      return match.matches(); 
   }       

   public boolean checkEmpty(String email, String password){       
      return  email.isEmpty() || password.isEmpty();   
   }

   public boolean validateEmail(String email){                       
      return validate(emailPattern,email);   
   }
   
   public boolean validatePhoneNumber(String phone){                       
      return validate(phoneNumberPattern, phone);   
   }
   
   public boolean validateCardNumber(String cardNumber){                       
      return validate(cardNumberPattern,cardNumber);   
   }
   
   public boolean validateExpiry(String expiry){                       
      return validate(expiryPattern,expiry);   
   }

   public void clear (HttpSession session){
       session.setAttribute("emailErr", "Enter Email");
       session.setAttribute("existErr", "");
       session.setAttribute("phoneErr", "Enter phone");

   }
}
