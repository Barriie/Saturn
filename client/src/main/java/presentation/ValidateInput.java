package presentation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nourredinne on 22-10-2015.
 */
public class ValidateInput {

    public boolean validateTime(String time) {
        boolean tempBool = false;

        //Regex 2 digits : 2 digits
        String TIME_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

        if(time.matches(TIME_PATTERN)) {
            tempBool = true;
        }

        return tempBool;
    }

    public boolean validateNumber(String number) {

        boolean statusemployeenr = false;

        //Regex; Only digits
        String EMPLOYEENR_PATTERN = "^\\d+$";

        if (number.matches(EMPLOYEENR_PATTERN)) {
            statusemployeenr = true;
        }

        return statusemployeenr;
    }

    public boolean validateZipCode(String zipCode) {

        boolean statuszipcode = false;

        //Regex; Dutch zipCode
        //String ZIPCODE_PATTERN = "/^[1-9][0-9]{3}[\\s]?[A-Za-z]{2}$/i";
        String ZIPCODE_PATTERN = "^\\d{4}\\s\\D{2}$";

        if (zipCode.matches(ZIPCODE_PATTERN)) {
            statuszipcode = true;
        }

        return statuszipcode;
    }

    public boolean validatePhoneNumber(String phone) {

        boolean statusphonenumber = false;

        //Regex Dutch phoneNumbers
        String PHONE_PATTERN = "/^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$/" + "/^(((\\\\+31|0|0031)6){1}[1-9]{1}[0-9]{7})$/i";

        if (phone.matches(PHONE_PATTERN)) {
            statusphonenumber = true;
        }

        return statusphonenumber;
    }

    public boolean validateName(String name) {

        boolean statuscity = false;

        //Regex for city's
        //String CITY_PATTERN = "/^(([2][e][[:space:]]|['][ts][-[:space:]]))?[����a-zA-Z]{2,}((\\s|[-](\\s)?)[����a-zA-Z]{2,})*$/i";
        String CITY_PATTERN = "^\\D+$";

        if (name.matches(CITY_PATTERN)) {
            statuscity = true;
        }

        return statuscity;
    }

    public boolean validateAdress(String adress) {

        boolean statusadress = false;

        String ADRESS_PATTERN = "/^([1-9][e][\\s])*([a-zA-Z]+(([\\.][\\s])|([\\s]))?)+[1-9][0-9]*(([-][1-9][0-9]*)|([\\s]?[a-zA-Z]+))?$/i";
        Pattern pattern = Pattern.compile(ADRESS_PATTERN);
        Matcher matcher = pattern.matcher(adress);

        if (matcher.matches()) {
            statusadress = true;
        }

        return statusadress;
    }

    public boolean validateBSN(String bsn) {

        boolean statusbsn = false;

        String BSN_PATTERN = "^[0-9]+$";
        Pattern pattern = Pattern.compile(BSN_PATTERN);
        Matcher matcher = pattern.matcher(bsn);

        if (matcher.matches()) {
            statusbsn = true;
        }

        return statusbsn;
    }

    public boolean validateEmail(String email) {

        boolean statusemail = false;

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\._A-Za-z0-0-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            statusemail = true;
        }

        return statusemail;
    }

    public boolean validateDateOfBirth(String dateOfBirth) {

        boolean statusdateofbirth = false;

        String DATEOFBIRTH_PATTERN = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

        if (dateOfBirth.matches(DATEOFBIRTH_PATTERN)) {
            statusdateofbirth = true;
        }

        return statusdateofbirth;
    }

}
