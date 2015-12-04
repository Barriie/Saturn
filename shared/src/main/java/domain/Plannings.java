package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvroe on 2-11-2015.
 */
@XmlRootElement(name = "plannings")
public class Plannings {

    List<Planning> plannings;

    public List<Planning> getPlannings() { return plannings;}

    @XmlElement(name = "planning")

    public void setPlannings( List<Planning> plannings)
    {
        this.plannings = plannings;
    }

    public void add( Planning planning )
    {
        if( this.plannings == null )
        {
            this.plannings = new ArrayList<Planning>();
        }
        this.plannings.add( planning );

    }

}

