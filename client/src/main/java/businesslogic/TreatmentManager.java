package businesslogic;

import domain.Employees;
import domain.Treatment;
import domain.Treatments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.events.DocumentEvent;

import java.io.File;
import java.io.InputStream;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import static javax.xml.bind.JAXBContext.*;


public class TreatmentManager {

    Treatments treatments = new Treatments();
    private  ObservableList<Treatment> data = Load();
    File file = new File("Saturn\\server\\src\\main\\resources\\xml\\treatment.xml");

    public TreatmentManager() {
    }

    public ObservableList<Treatment> Load()
    {
        ObservableList<Treatment> returnList = FXCollections.observableArrayList();

        try {
            File tempFile = new File("Saturn\\server\\src\\main\\resources\\xml\\treatment.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Treatments.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            treatments = (Treatments) jaxbUnmarshaller.unmarshal(tempFile);

            returnList.addAll(treatments.getTreatments().stream().collect(Collectors.toList()));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return returnList;
    }

    public boolean Save() {
        boolean tempBool = true;


        try {
            File tempFile = new File("Saturn\\server\\src\\main\\resources\\xml\\treatment.xml");
            Treatments tempList = new Treatments();
            data.forEach(tempList::add);

            JAXBContext jaxbContext = newInstance(Treatments.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(tempList, tempFile);


        } catch (JAXBException e) {
            e.printStackTrace();
            tempBool = false;
        }
        return tempBool;
    }
}