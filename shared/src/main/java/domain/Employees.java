package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvroe on 2-11-2015.
 */
@XmlRootElement(name = "employees")
public class Employees {

    List<Employee> employees = new ArrayList<>();
    public List<Employee> getEmployees() { return employees;}

    @XmlElement(name = "employee")
    public void setEmployees( List<Employee> employees)
    {
        this.employees = employees;
    }

    public void add( Employee employee )
    {
        if( this.employees == null )
        {
            this.employees = new ArrayList<Employee>();
        }
        this.employees.add( employee );

    }

}