package datastorage;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;

/**
 * Created by Barrie on 4-12-2015.
 */
public class TimeAdapter extends XmlAdapter<String, LocalTime>
{

        @Override
        public LocalTime unmarshal(String time) throws Exception {
                return LocalTime.parse(time);
        }

        @Override
        public String marshal(LocalTime time) throws Exception {
                return time.toString();
        }
}
