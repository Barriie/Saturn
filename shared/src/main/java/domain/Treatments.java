package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvroe on 2-11-2015.
 */
@XmlRootElement(name = "treatments")
public class Treatments {

    List<Treatment> treatments;

    public List<Treatment> getTreatments() { return treatments;}

    @XmlElement(name = "treatment")

   public void setTreatments( List<Treatment> treatments)
    {
        this.treatments = treatments;
    }

    public void add( Treatment treatment )
    {
        if( this.treatments == null )
        {
            this.treatments = new ArrayList<Treatment>();
        }
        this.treatments.add( treatment );

    }

    @Override
    public String toString()
    {
        StringBuffer str = new StringBuffer();
        for( Treatment treatmentlist : this.treatments )
        {
            str.append( treatmentlist.toString() );
        }
        return str.toString();
    }


}
