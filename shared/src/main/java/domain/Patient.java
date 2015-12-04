package domain;

import datastorage.DateAdapter;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "patient")
public class Patient {
    //region Attributes and properties
    private SimpleIntegerProperty patientBSN = new SimpleIntegerProperty(this, "patientBSN");
    private SimpleStringProperty patientFirstName = new SimpleStringProperty(this, "patientFirstName");
    private SimpleStringProperty patientLastName = new SimpleStringProperty(this, "patientLastName");
    private SimpleStringProperty patientFullName = new SimpleStringProperty(this, "patientFullName");
    private SimpleStringProperty patientCity = new SimpleStringProperty(this, "patientCity");
    private SimpleStringProperty patientCountry = new SimpleStringProperty(this, "patientCountry");
    private SimpleStringProperty patientStreet = new SimpleStringProperty(this, "patientStreet");
    private SimpleStringProperty patientHouseNumber = new SimpleStringProperty(this, "patientHouseNumber");
    private SimpleStringProperty patientAddress  = new SimpleStringProperty(this, "patientAddress");
    private LocalDate patientDateOfBirth = LocalDate.now();
    private SimpleStringProperty patientZipCode = new SimpleStringProperty(this, "patientZipCode");
    private SimpleStringProperty patientPhone = new SimpleStringProperty(this, "patientPhone");
    private SimpleStringProperty patientEmail = new SimpleStringProperty(this, "patientEmail");

    private ArrayList<Treatment> patientTreatments;
    //endregion

    public Patient() {
    }

    //region Methods
    public Patient(int patientBsn, String patientFirstName, String patientLastName, String patientCity, String patientCountry, String patientStreet, String patientHouseNumber,
                   LocalDate patientDateOfBirth, String patientZipCode, String patientPhone, String patientEmail) {
        this.patientBSN.set(patientBsn);
        this.patientFirstName.set(patientFirstName);
        this.patientLastName.set(patientLastName);
        this.patientFullName.set(patientFirstName + " " + patientLastName);
        this.patientCity.set(patientCity);
        this.patientHouseNumber.set(patientHouseNumber);
        this.patientStreet.set(patientStreet);
        this.patientAddress.set(patientStreet + " " + patientHouseNumber + " " + patientCity);
        this.patientCountry.set(patientCountry);
        this.patientDateOfBirth = patientDateOfBirth;
        this.patientZipCode.set(patientZipCode);
        this.patientPhone.set(patientPhone);
        this.patientEmail.set(patientEmail);
    }
    //endregion

    //region Getters and setters

    public String getPatientFullName() {
        return patientFullName.get();
    }

    public SimpleStringProperty patientFullNameProperty() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName.set(patientFullName);
    }

    @XmlElement(name = "ssn")
    public int getPatientBSN() {
        return patientBSN.get();
    }

    public SimpleIntegerProperty patientBSNProperty() {
        return patientBSN;
    }

    public void setPatientBSN(int patientBSN) {
        this.patientBSN.set(patientBSN);
    }

    @XmlElement(name = "firstName")
    public String getPatientFirstName() {
        return patientFirstName.get();
    }

    public SimpleStringProperty patientFirstNameProperty() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName.set(patientFirstName);
    }

    @XmlElement(name = "lastName")
    public String getPatientLastName() {
        return patientLastName.get();
    }

    public SimpleStringProperty patientLastNameameProperty() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName.set(patientLastName);
    }

    @XmlElement(name = "address")
    public String getPatientAddress() {
        return patientAddress.get();
    }

    public SimpleStringProperty patientAddressProperty() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress.set(patientAddress);
    }

    @XmlAttribute(name = "city")
    public String getPatientCity() {
        return patientCity.get();
    }

    public SimpleStringProperty patientCityProperty() {
        return patientCity;
    }

    public void setPatientCity(String patientCity) {
        this.patientCity.set(patientCity);
    }

    @XmlAttribute(name = "houseNumber")
    public String getPatientHouseNumber() {
        return patientHouseNumber.get();
    }

    public SimpleStringProperty getPatientHouseNumberProperty() {
        return patientHouseNumber;
    }

    public void setPatientHouseNumber(String patientHouseNumber) {
        this.patientHouseNumber.set(patientHouseNumber);
    }

    @XmlAttribute(name = "country")
    public String getPatientCountry() {
        return patientCountry.get();
    }

    public SimpleStringProperty patientCountryProperty() {
        return patientCountry;
    }

    public void setPatientCountry(String patientCountry) {
        this.patientCountry.set(patientCountry);
    }

    @XmlAttribute(name = "street")
    public String getPatientStreet() {
        return patientStreet.get();
    }

    public SimpleStringProperty patientStreetProperty() {
        return patientStreet;
    }

    public void setPatientStreet(String patientStreet) {
        this.patientStreet.set(patientStreet);
    }

    @XmlAttribute(name = "postalCode")
    public String getPatientZipCode() {
        return patientZipCode.get();
    }

    public SimpleStringProperty patientZipCodeProperty() {
        return patientZipCode;
    }

    public void setPatientZipCode(String patientZipCode) {
        this.patientZipCode.set(patientZipCode);
    }

    @XmlElement(name = "dateOfBirth")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public LocalDate getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(LocalDate patientDateOfBirth) {
        this.patientDateOfBirth = patientDateOfBirth;
    }

    @XmlElement(name = "phone")
    public String getPatientPhone() {
        return patientPhone.get();
    }

    public SimpleStringProperty patientPhoneProperty() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone.set(patientPhone);
    }

    @XmlElement(name = "email")
    public String getPatientEmail() {
        return patientEmail.get();
    }

    public SimpleStringProperty patientEmailProperty() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail.set(patientEmail);
    }

    public ArrayList<Treatment> getPatientTreatments() {
        return patientTreatments;
    }

    public void setPatientTreatments(ArrayList<Treatment> patientTreatments) {
        this.patientTreatments = patientTreatments;
    }
    //endregion
}