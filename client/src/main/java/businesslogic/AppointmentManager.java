package businesslogic;

import domain.Appointment;
import domain.AppointmentList;
import domain.Employee;
import domain.Employees;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

import static javax.xml.bind.JAXBContext.newInstance;


/**
 * Created by Barrie on 31-10-2015.
 */
public class AppointmentManager {

    //region Attributes and propperties
    private ObservableList<Appointment> data = Load();
    private AppointmentList appointmentList = new AppointmentList();
    //endregion
    File file = new File("Saturn\\server\\src\\main\\resources\\xml\\appointment.xml");

    //region Methods
    public AppointmentManager() {
    }

    public ObservableList<Appointment> Load()
    {
        ObservableList<Appointment> returnList = FXCollections.observableArrayList();

        try {
            File tempfile = new File("Saturn\\server\\src\\main\\resources\\xml\\appointment.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(AppointmentList.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            appointmentList = (AppointmentList) jaxbUnmarshaller.unmarshal(tempfile);

            returnList.addAll(appointmentList.getAppointments().stream().collect(Collectors.toList()));


        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return returnList;
    }

    public boolean Save() {
        boolean tempBool = true;

        try {
            File tempFile = new File("Saturn\\server\\src\\main\\resources\\xml\\appointment.xml");
            AppointmentList tempList = new AppointmentList();
            data.forEach(tempList::add);

            JAXBContext jaxbContext = newInstance(AppointmentList.class);
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
     * @return List with all the data
     */
    public ObservableList<Appointment> getData() {
        return data;
    }

    /**
     * @param appointment An appointment object
     * @return True if the appointment has been added
     */
    public boolean addApointment(Appointment appointment) {
        Appointment tempAppointment = searchWithAppointmentNumber(appointment.getAppointmentNumber());
        boolean tempBool = false;

        if (tempAppointment == null) {
            data.add(appointment);
            tempBool = true;
        }

        return tempBool;
    }

    /**
     * @param appointment An appointment object
     * @return True if the appointment has been removed
     */
    public boolean deleteAppointment(Appointment appointment) {
        Appointment tempAppointment = searchWithAppointmentNumber(appointment.getAppointmentNumber());
        boolean tempBool = false;

        if (tempAppointment != null) {
            data.remove(appointment);
            tempBool = true;
        }

        return tempBool;
    }

    /**
     * @param appointmentNr Number belonging to an appointment
     * @return The appointment the appointmentNr belongs to
     */
    public Appointment searchWithAppointmentNumber(String appointmentNr) {
        Appointment tempAppointment = null;

        for (Appointment a : data) {
            if (a.getAppointmentNumber() == appointmentNr) {
                tempAppointment = a;
            }
        }

        return tempAppointment;
    }

    /**
     * @param workDate Date in dd/MM/yyyy
     * @return A list with all the appointmentList that day
     */
    public ObservableList<Appointment> searchWithWorkDate(LocalDate workDate) {
        ObservableList<Appointment> tempAppointments = FXCollections.observableArrayList();
        for (Appointment a : data) {
            if (a.getAppointmentDate().equals(workDate)) {
                tempAppointments.add(a);
            }
        }
        return tempAppointments;
    }

    /**
     * @param BSN BSN belonging to patient
     * @return A list with all the appointmentList for the patient belonging to the BSN
     */
    public ObservableList<Appointment> searchWithPatientBSN(int BSN) {
        ObservableList<Appointment> tempAppointments = FXCollections.observableArrayList();
        for (Appointment a : data) {
            if (a.getAppointmentPatient().getPatientBSN().equals(BSN)) {
                tempAppointments.add(a);
            }
        }
        return tempAppointments;
    }

    /**
     * @param workDate     Date in dd/MM/yyyy
     * @param employeeName Name belonging to an employee
     * @return A list with all the appointmentList that day with that employee
     */
    public ObservableList<Appointment> searchWithWorkDateAndEmployeeName(LocalDate workDate, String employeeName) {
        ObservableList<Appointment> tempAppointments = FXCollections.observableArrayList();
        for (Appointment a : data) {
            if (a.getAppointmentDate().equals(workDate) && a.getAppointmentFysio().getEmployeeName().equals(employeeName)) {
                tempAppointments.add(a);
            }
        }
        return tempAppointments;
    }
    //endregion
}
