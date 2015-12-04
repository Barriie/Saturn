package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvroe on 2-11-2015.
 */
    @XmlRootElement(name = "workdays")
    public class Workdays {

        List<Workday> workdays;

        public List<Workday> getWorkdays() { return workdays;}

        @XmlElement(name = "workday")

        public void setWorkdays( List<Workday> workdays)
        {
            this.workdays = workdays;
        }

        public void add( Workday workday )
        {
            if( this.workdays == null )
            {
                this.workdays = new ArrayList<Workday>();
            }
            this.workdays.add( workday );

        }

    }
