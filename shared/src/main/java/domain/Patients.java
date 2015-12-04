package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvroe on 2-11-2015.
 */
@XmlRootElement(name = "patients")
public class Patients {

    List<Patient> patients;
    public List<Patient> getPatients() { return patients;}

    @XmlElement(name = "patient")
    public void setPatients( List<Patient> patients)
    {
        this.patients = patients;
    }

    public void add( Patient patient )
    {
        if( this.patients == null )
        {
            this.patients = new ArrayList<Patient>();
        }
        this.patients.add( patient );

    }

}