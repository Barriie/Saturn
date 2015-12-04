package businesslogic;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import domain.Employee;
import domain.Employees;
import domain.Workday;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static javax.xml.bind.JAXBContext.newInstance;

/**
 * Created by Barrie on 22-10-2015.
 */
public class EmployeeManager {
    //region Attributes and properties
    private final ObservableList<Employee> data = Load();
    Employees employees = new Employees();
    File file = new File("Saturn\\server\\src\\main\\resources\\xml\\employee.xml");

    //endregion

    //region Methods
    public EmployeeManager() {
    }

    public ObservableList<Employee> Load()
    {
        ObservableList<Employee> returnList = FXCollections.observableArrayList();

        try {
            File tempFile = new File("Saturn\\server\\src\\main\\resources\\xml\\employee.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            employees = (Employees) jaxbUnmarshaller.unmarshal(tempFile);

            returnList.addAll(employees.getEmployees().stream().collect(Collectors.toList()));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
           return returnList;
    }

    public boolean Save()
    {
        boolean tempBool = true;


        try {
            File tempFile = new File("Saturn\\server\\src\\main\\resources\\xml\\employee.xml");
            Employees tempList = new Employees();
            data.forEach(tempList::add);

            JAXBContext jaxbContext = newInstance(Employees.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(tempList, tempFile);

        } catch (JAXBException e) {
            e.printStackTrace();
            tempBool = false;
        }
        return tempBool;
    }

    /**
     *
     * @return List with all the data
     */
    public ObservableList<Employee> getData() {
        return data;
    }

    /**
     *
     * @return List with all the names of employees
     */
    public ObservableList<String> getEmployeeNames() {
        ObservableList<String> names = FXCollections.observableArrayList();

        names.addAll(data.stream().map(Employee::getEmployeeName).collect(Collectors.toList()));

        return names;
    }

    /**
     *
     * @param employee An employee object
     * @return True if employee has been added
     */
    public boolean addEmployee(Employee employee) {
        Employee oldEmployee = searchEmployeeWithNumber(employee.getEmployeeNr());
        boolean returnBoolean = false;

        if (oldEmployee == null) {
            data.add(employee);
            returnBoolean = true;
        }
        return returnBoolean;
    }

    /**
     *
     * @param employee An employee object
     * @return True if employee has been removed
     */
    public boolean deleteEmployee(Employee employee) {
        Employee returnEmployee = searchEmployeeWithNumber(employee.getEmployeeNr());
        boolean returnBoolean = false;

        if (returnEmployee != null) {
            data.remove(employee);
            returnBoolean = true;
        }
        return returnBoolean;
    }

    /**
     *
     * @param employeeNr Number belonging to an employee
     * @return The employee the employeeNr belongs to
     */
    public Employee searchEmployeeWithNumber(String employeeNr) {
        Employee returnEmployee = null;
        for (Employee e : data) {
            if (e.getEmployeeNr().equals(employeeNr)) {
                returnEmployee = e;
            }
        }
        return returnEmployee;
    }

    /**
     *
     * @param employeeName Name belonging to an employee
     * @return The employee the employeeName belongs to
     */
    public Employee searchEmployeeWithName(String employeeName) {
        Employee returnEmployee = null;
        for (Employee e : data) {
            if (e.getEmployeeName().equals(employeeName)) {
                returnEmployee = e;
            }
        }
        return returnEmployee;
    }

    /**
     *
     * @param employeeNr Number belonging to an employee
     * @param workday An workday object
     * @return True if the workday has been added to the employee
     */
    public boolean addWorkday(String employeeNr, Workday workday) {
        boolean tempBool = false;
        Employee tempEmployee = searchEmployeeWithNumber(employeeNr);

        if (tempEmployee != null) {
            tempEmployee.addWorkday(workday);
            tempBool = true;
        }

        return tempBool;
    }

    /**
     *
     * @param employeeNr Number belonging to an employee
     * @param workday An workday object
     * @return True if the workday has been removed from the employee
     */
    public boolean removeWorkday(String employeeNr, Workday workday) {
        boolean tempBool = false;
        Employee tempEmployee = searchEmployeeWithNumber(employeeNr);

        if (tempEmployee != null) {
            tempEmployee.removeWorkday(workday);
            tempBool = true;
        }

        return tempBool;
    }

    /**
     *
     * @param employeeNr
     * @param workDate
     * @return
     */
    public Workday searchWorkdayWithDate(String employeeNr, LocalDate workDate) {
        Workday tempWorkday = null;
        Employee tempEmployee = searchEmployeeWithNumber(employeeNr);

        if (tempEmployee != null) {
            tempWorkday = tempEmployee.searchWorkdayByDate(workDate);
        }

        return tempWorkday;
    }
    //endregion
}
