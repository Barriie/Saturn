package businesslogic;

import domain.Patient;
import domain.Patients;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.stream.Collectors;

import static javax.xml.bind.JAXBContext.newInstance;

public class PatientManager {
    //region Attributes and properties
    private Patients patients = new Patients();
    private ObservableList<Patient> data = Load();
    File file = new File("Saturn\\server\\src\\main\\resources\\xml\\patient.xml");
    //endregion

    //region Methods
    public PatientManager() {

    }

    public ObservableList<Patient> Load() {
        ObservableList<Patient> returnList = FXCollections.observableArrayList();

        try {
            File tempFile = new File("Saturn\\server\\src\\main\\resources\\xml\\patient.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Patients.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            patients = (Patients) jaxbUnmarshaller.unmarshal(tempFile);

            returnList.addAll(patients.getPatients().stream().collect(Collectors.toList()));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return returnList;
    }

    public boolean Save() {
        boolean tempBool = true;
        try {
            File tempFile = new File("Saturn\\server\\src\\main\\resources\\xml\\patient.xml");
            Patients tempList = new Patients();
            for (Patient t : data) {
                tempList.add(t);
            }

            JAXBContext jaxbContext = newInstance(Patients.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(tempList, tempFile);


        } catch (JAXBException e) {
            e.printStackTrace();
            tempBool = false;
        }
        return tempBool;
    }

    public ObservableList<Patient> getData() {
        return data;
    }

    /**
     * @return List with all the names of patients
     */
    public ObservableList<String> getPatientNames() {
        ObservableList<String> names = FXCollections.observableArrayList();

        for (Patient e : data) {
            names.add(e.getPatientFullName());
        }

        return names;
    }

    /**
     * @param patientName Name belonging to an patient
     * @return The patient the patientName belongs to
     */
    public Patient searchPatientWithName(String patientName) {
        Patient returnPatient = null;
        for (Patient e : data) {
            if (e.getPatientFullName() != null && e.getPatientFullName().equals(patientName)) {
                returnPatient = e;
            }
        }
        return returnPatient;
    }

    /**
     * @param patient A patient object
     * @return True if the patient has been added
     */
    public boolean addPatient(Patient patient) {
        Patient tempPatient = searchWithBSN(patient.getPatientBSN());
        boolean tempBool = false;

        if (tempPatient == null) {
            data.add(patient);
            tempBool = true;
        }

        return tempBool;
    }

    /**
     * @param patient A patient object
     * @return Returns true if the patient has been removed
     */
    public boolean removePatient(Patient patient) {
        Patient tempPatient = searchWithBSN(patient.getPatientBSN());
        boolean tempBool = false;

        if (tempPatient != null) {
            data.remove(patient);
            tempBool = true;
        }

        return tempBool;
    }

    /**
     * @param BSN The BSN belonging to the patient
     * @return The patient the BSN belongs to
     */
    public Patient searchWithBSN(String BSN) {
        Patient tempPatient = null;

        for (Patient p : data) {
            if (p.getPatientBSN() != null) {
                if (p.getPatientBSN().equals(BSN)) {
                    tempPatient = p;
                }
            }
        }

        return tempPatient;
    }
    //endregion
}