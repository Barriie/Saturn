package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvroe on 2-11-2015.
 */
@XmlRootElement(name = "appointments")
public class AppointmentList {

    List<Appointment> appointments = new ArrayList<>();
    public List<Appointment> getAppointments() { return appointments;}

    @XmlElement(name = "appointment")
    public void setAppointments( List<Appointment> appointments)
    {
        this.appointments = appointments;
    }

    public void add( Appointment appointment )
    {
        if( this.appointments == null )
        {
            this.appointments = new ArrayList<Appointment>();
        }
        this.appointments.add( appointment );

    }

}
