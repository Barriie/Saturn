package datastorage;

/**
 * Created by rvroe on 3-11-2015.
 */
import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class DateAdapter extends XmlAdapter<String, LocalDate>
{

    public LocalDate unmarshal( String date ) throws Exception
    {
        return LocalDate.parse( date );
    }


    public String marshal( LocalDate date ) throws Exception
    {
        return date.toString();
    }

}

