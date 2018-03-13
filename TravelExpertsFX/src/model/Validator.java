package model;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Validator {

	public static boolean IsTxTEmpty(TextField txt, String label){
    	boolean valid = true;
    	
    	if(txt.getText().isEmpty()) {
    		valid = false;
    		Alert alert = new Alert(AlertType.ERROR);
    	    alert.setTitle("Entry Error");   
    	    String s =  label + " is required. \nEnter valid text and save. ";
    	    alert.setContentText(s);
    	    alert.showAndWait();
    	    txt.requestFocus();
    	}    	
    	return valid;
	}
	
    public static boolean isValidPostal(String s){      
        String regex="^[A-Za-z]\\d[A-Za-z][ -]?\\d[A-Za-z]\\d$";      
        return s.matches(regex);
    }
    
    public static boolean isValidEmail(String s){      
    	String regexEmail= "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return s.matches(regexEmail);
    }
    
	public static boolean IsTxTValid(TextField txt, String label,String regex){
    	boolean valid = true;
    	
    	if(!txt.getText().trim().matches(regex)) {
    		valid = false;
    		Alert alert = new Alert(AlertType.ERROR);
    	    alert.setTitle("Entry Error");   
    	    String s =  label + " is not valid. \nEnter valid text and save. ";
    	    alert.setContentText(s);
    	    alert.showAndWait();
    	    txt.requestFocus();
    	}    	
    	return valid;
	}
	
//  	public static boolean isValidEmailAddress(TextField txt, String label,String email) {
//   	   boolean valid = true;
//   	   try {
//   		   System.out.println(email);
//   	      InternetAddress emailAddr = new InternetAddress(email);
//   	      emailAddr.validate();
//   	   } catch (AddressException ex) {
//   		valid = false;
//   	    Alert alert = new Alert(AlertType.ERROR);
//	    alert.setTitle("Entry Error");   
//	    String s =  label + " is not valid. \nEnter valid text and save. ";
//	    alert.setContentText(s);
//	    alert.showAndWait();
//	    txt.requestFocus();
//   	   }
//   	   return valid;
//   	}


}
