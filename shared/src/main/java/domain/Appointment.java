package domain;

import datastorage.DateAdapter;
import datastorage.TimeAdapter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@XmlRootElement(name = "appointment")
public class Appointment {
    //region Attributes and properties
    private String appointmentNumber;
    private LocalDate appointmentDate;

    private Employee appointmentFysio;
    private Patient appointmentPatient;

    private String appointmentFysioName;
    private String appointmentPatientName;
    private String appointmentStartTimeString;
    private String appointmentStopTimeString;
    //endregion

    public Appointment(){

    }


    //region Methods
    public Appointment(String appointmentNumber, LocalDate appointmentDate, LocalTime startTime, LocalTime stopTime, Employee fysio, Patient patient) {
        this.appointmentNumber = appointmentNumber;
        this.appointmentDate = appointmentDate;
        this.appointmentFysio = fysio;
        this.appointmentPatient = patient;

        setAppointmentFysioName(fysio.getEmployeeName());
        setAppointmentPatientName(patient.getPatientFullName());

        String TIME_PATTERN = "HH:mm";
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(TIME_PATTERN);
        setAppointmentStartTimeString(timeFormat.format(startTime));
        setAppointmentStopTimeString(timeFormat.format(stopTime));
    }

    public String ToString() {
        return appointmentNumber + " op: " + appointmentDate + " heeft Fysiotherapeut: " +
                appointmentFysio.getEmployeeName() + " van: " + appointmentStartTimeString + " tot: " + appointmentStopTimeString + " patient: " + appointmentPatient.getPatientFullName() + " behandeld";
    }
    //endregion

    //region Getters and setters
    @XmlElement(name = "appointmentNumber")
    public String getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(String appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    @XmlElement(name = "appointmentDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }


    public Employee getAppointmentFysio() {
        return appointmentFysio;
    }

    public void setAppointmentFysio(Employee appointmentFysio) {
        this.appointmentFysio = appointmentFysio;
    }

    public Patient getAppointmentPatient() {
        return appointmentPatient;
    }

    public void setAppointmentPatient(Patient appointmentPatient) {
        this.appointmentPatient = appointmentPatient;
    }

    public String getAppointmentFysioName() {
        return appointmentFysioName;
    }

    public void setAppointmentFysioName(String appointmentFysioName) {
        this.appointmentFysioName = appointmentFysioName;
    }

    public String getAppointmentPatientName() {
        return appointmentPatientName;
    }

    public void setAppointmentPatientName(String appointmentPatientName) {
        this.appointmentPatientName = appointmentPatientName;
    }

    public String getAppointmentStartTimeString() {
        return appointmentStartTimeString;
    }

    public void setAppointmentStartTimeString(String appointmentStartTimeString) {
        this.appointmentStartTimeString = appointmentStartTimeString;
    }

    public String getAppointmentStopTimeString() {
        return appointmentStopTimeString;
    }

    public void setAppointmentStopTimeString(String appointmentStopTimeString) {
        this.appointmentStopTimeString = appointmentStopTimeString;
    }

    //endregion
}
