
package com.thanhpdc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class checkValue {
    
    public static boolean checkEmpId(String EmpID) {
        if (EmpID.contains("#") || EmpID.contains("@") || EmpID.contains("$") || EmpID.length() > 10) {
            return false;
        }
        
        return true;
    }
    
    public static boolean checkFullName(String fullName) {
        if (fullName.length() > 30) {
            return false;
        }
        return true;
    }
    
    public static boolean checkAddress(String address) {
        return address.length() <= 300;
    }
    
    public static boolean checkDateOfBirth(String dateOfBirth) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            if (!dateOfBirth.matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")) {
                return false;
            } else if (!format.parse(dateOfBirth).before(Calendar.getInstance().getTime())) {
                return false;
            }
        } catch (Exception e) {
        }
        return true;
    }
    
    public static boolean checkPhone(String phone) {
        return phone.matches("\\d{2,15}");
    }
    
    public static boolean checkEmail(String email) {
        String check = "^[a-z][a-z0-9_\\.]{2,15}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
        String email0 = email.charAt(0) + "";
        if (email.contains("#") || email.contains("!") || email.contains("$")) {
            return false;
        } else if (email.length() > 30) {
            return false;
        } else if (!email.matches(check)) {
            return false;
        }else if(!email0.matches("[^0-9]")){
            return false;
        }
        //check email
        return true;
    }
    
}
