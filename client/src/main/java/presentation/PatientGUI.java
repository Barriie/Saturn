package presentation;

import businesslogic.AppointmentManager;
import businesslogic.DataManager;
import businesslogic.PatientManager;
import domain.Appointment;
import domain.Patient;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;

public class PatientGUI extends Application {
    //region Attributes and properties
    private TableView<Patient> table;
    private DataManager dataManager = new DataManager();
    private ValidateInput validate;

    private VBox vBox = new VBox();
    private BorderPane borderPane = new BorderPane();
    private GridPane gridPane = new GridPane();

    private TabPane pane = new TabPane();
    private Tab employeeTab = new Tab("Medewerker");
    private Tab appointmentTab = new Tab("Afspraak");
    private Tab customerTab = new Tab("Patient");
    private Tab manageEmployeeTab = new Tab("Overzicht Werknemers");

    private TextField patientBSN;
    private Button searchButton;

    private static Label BSN;
    private static Label NAME;
    private static Label ADDRESS;
    private static Label ZIPCODE;
    private static Label CITY;
    private static Label DATEOFBIRTH;
    private static Label PHONE;
    private static Label EMAIL;
    private Label bsnLabel;
    private Label nameLabel;
    private Label adressLabel;
    private Label zipcodeLabel;
    private Label cityLabel;
    private Label dateOfBirthLabel;
    private Label phoneLabel;
    private Label emailLabel;

    TableColumn bsnCol = new TableColumn("Bsn");
    TableColumn fullNameCol = new TableColumn("Naam");
    TableColumn addressCol = new TableColumn("Adres");
    TableColumn countryCol = new TableColumn("Land");
    TableColumn dateOfBirthCol = new TableColumn("Geboortedatum");
    TableColumn zipCodeCol = new TableColumn("Postcode");
    TableColumn phoneCol = new TableColumn("Telefoonnummer");
    TableColumn mailCol = new TableColumn("E-mail");
    //endregion

    @Override
    public void start(Stage stage) throws Exception {
        table = new TableView<>();
        validate = new ValidateInput();

        stage.setTitle("Fysio App");

        table.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
                p -> new EditingCellManageEmployee();

        //region Creating tabs
        pane = new TabPane();
        appointmentTab = new Tab("Afspraak");
        customerTab = new Tab("Patient");
        employeeTab = new Tab("Medewerker");
        manageEmployeeTab = new Tab("Overzicht Werknemers");

        pane.getSelectionModel().select(customerTab);

        //adding action listeners
        pane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue.getText()) {
                case "Afspraak":
                    AppointmentGUI guiAppointment = new AppointmentGUI();
                    try {
                        dataManager.Save();
                        guiAppointment.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Medewerker":
                    EmployeeGUI guiEmployee = new EmployeeGUI();
                    try {
                        dataManager.Save();
                        guiEmployee.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Patient":
                    break;
                case "Overzicht Werknemers":
                    ManageEmployeeGUI guiManageEmployee = new ManageEmployeeGUI();
                    try {
                        dataManager.Save();
                        guiManageEmployee.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        });
        //endregion

        //region Creating columns for tables

        bsnCol.setCellValueFactory(
                new PropertyValueFactory<Patient, String>("patientBSN"));
        fullNameCol.setCellFactory(
                new PropertyValueFactory<Patient, String>("patientFullName"));
        addressCol.setCellFactory(
                new PropertyValueFactory<Patient, String>("patientAddress"));
        countryCol.setCellFactory(
                new PropertyValueFactory<Patient, String>("patientCountry"));
        dateOfBirthCol.setCellValueFactory(
                new PropertyValueFactory<Patient, String>("patientDateOfBirth"));
        zipCodeCol.setCellFactory(
                new PropertyValueFactory<Patient, String>("patientZipCode"));
        phoneCol.setCellFactory(
                new PropertyValueFactory<Patient, String>("patientPhone"));
        mailCol.setCellFactory(
                new PropertyValueFactory<Patient, String>("patientEmail"));
        //endregion

        //region Creating textfields
        final TextField addBsn = new TextField();
        addBsn.setMaxWidth(bsnCol.getPrefWidth());
        addBsn.setPromptText("BSN");

        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("Voornaam");

        final TextField addLastName = new TextField();
        addLastName.setPromptText("Achternaam");

        final TextField addCity = new TextField();
        addCity.setPromptText("Stad");

        final TextField addHouseNumber = new TextField();
        addHouseNumber.setPromptText("Huisnummer");

        final TextField addStreet = new TextField();
        addStreet.setPromptText("Straat");

        final TextField addDateOfBirth = new TextField();
        addDateOfBirth.setPromptText("Geboortedatum");

        final TextField addZipCode = new TextField();
        addZipCode.setPromptText("Postcode");

        final TextField addPhone = new TextField();
        addPhone.setPromptText("Telefoonnummer");

        final TextField addMail = new TextField();
        addMail.setPromptText("E-mail");
        //endregion

        //region Creating buttons
        BSN = new Label("BSN:");
        NAME = new Label("Naam:");
        ADDRESS = new Label("Adres:");
        ZIPCODE = new Label("Postcode:");
        CITY = new Label("Woonplaats:");
        DATEOFBIRTH = new Label("Geboorte datum:");
        PHONE = new Label("Telefoon nummer:");
        EMAIL = new Label("Email-adres:");

        bsnLabel = new Label();
        nameLabel = new Label();
        adressLabel = new Label();
        zipcodeLabel = new Label();
        cityLabel = new Label();
        dateOfBirthLabel = new Label();
        phoneLabel = new Label();
        emailLabel = new Label();

        bsnLabel.setMinWidth(100);
        nameLabel.setMinWidth(100);
        adressLabel.setMinWidth(100);
        zipcodeLabel.setMinWidth(100);
        cityLabel.setMinWidth(100);
        dateOfBirthLabel.setMinWidth(100);
        phoneLabel.setMinWidth(100);
        emailLabel.setMinWidth(100);

        Button removeButton = new Button("Verwijder");
        removeButton.setOnAction(e -> {

        });

        Button addButton = new Button("Voeg Toe");
        addButton.setOnAction(e -> {
            Patient tempPatient = null;
            if (!addBsn.getText().equals("")) {
                tempPatient = dataManager.patientManager.searchWithBSN(addBsn.getText());
            } else {
                AlertBox.display("Error", "Er is geen bsn ingevuld");
            }

            if (tempPatient != null) {
                AlertBox.display("Error", "Patient met BSN: " + tempPatient.getPatientBSN() + "bestaat al onder de naam: " + tempPatient.getPatientFullName());
            } else if (!validate.validateNumber(addBsn.getText())) {
                AlertBox.display("Error", addBsn.getText() + " is geen geldig bsn");
                addBsn.clear();
            } else if (addFirstName.getText().equals("")) {
                AlertBox.display("Error", "Er is geen voornaam ingevuld");
            } else if (!validate.validateName(addFirstName.getText())) {
                AlertBox.display("Error", "Voornaam mag alleen uit letters bestaan");
                addFirstName.clear();
            } else if (addLastName.getText().equals("")) {
                AlertBox.display("Error", "Er is geen achternaam ingevuld");
            } else if (!validate.validateName(addLastName.getText())) {
                AlertBox.display("Error", "Achternaam mag alleen uit letters bestaan");
                addLastName.clear();
            } else if (addStreet.getText().equals("")) {
                AlertBox.display("Error", "Er is geen adres ingevuld");
            } else if (!validate.validateName(addStreet.getText())) {
                AlertBox.display("Error", "Adres mag alleen letters bevatten");
                addStreet.clear();
            } else if (addHouseNumber.getText().equals("")) {
                AlertBox.display("Error", "Er is geen huisnummer ingevuld");
            } else if (!validate.validateNumber(addHouseNumber.getText())) {
                AlertBox.display("Error", "Huisnummer mag alleen cijfers bevatten");
                addHouseNumber.clear();
            } else if (addCity.getText().equals("")) {
                AlertBox.display("Error", "Er is geen stad ingevuld");
            } else if (!validate.validateName(addCity.getText())) {
                AlertBox.display("Error", addCity.getText() + " is geen geldige stad");
                addCity.clear();
            } else if (addZipCode.getText().equals("")) {
                AlertBox.display("Error", "Er is geen postcode ingevuld");
            } else if (!validate.validateZipCode(addZipCode.getText())) {
                AlertBox.display("Error", addZipCode.getText() + " is geen geldige postcode");
                addZipCode.clear();
            } else if (addDateOfBirth.getText().equals("")) {
                AlertBox.display("Error", "Er is geen geboortedatum ingevuld");
            } else if (!validate.validateDateOfBirth(addDateOfBirth.getText())) {
                AlertBox.display("Error", addDateOfBirth.getText() + " is geen geldige geboortedatum");
                addDateOfBirth.clear();
            } else if (addPhone.getText().equals("")) {
                AlertBox.display("Error", "Er is geen telefoonnummer ingevuld");
            } else if (!validate.validateNumber(addPhone.getText())) {
                AlertBox.display("Error", addPhone.getText() + " is geen geldig telefoonnummer \n(Let op: U mag geen - gebruiken!");
                addPhone.clear();
            } else if (addMail.getText().equals("")) {
                AlertBox.display("Error", "Er is geen email ingevuld");
            } else {
                Patient newPatient = new Patient(
                        addBsn.getText(),
                        addFirstName.getText(),
                        addLastName.getText(),
                        addCity.getText(),
                        addHouseNumber.getText(),
                        addStreet.getText(),
                        "Nederland",
                        addDateOfBirth.getText(),
                        addZipCode.getText(),
                        addPhone.getText(),
                        addMail.getText()
                );
                if (!dataManager.patientManager.addPatient(newPatient)) {
                    AlertBox.display("Error", "Patient" + newPatient.getPatientFullName() + " is niet toegevoegd");
                } else {
                    addFirstName.clear();
                    addLastName.clear();
                    addBsn.clear();
                    addHouseNumber.clear();
                    addCity.clear();
                    addStreet.clear();
                    addDateOfBirth.clear();
                    addZipCode.clear();
                    addPhone.clear();
                    addMail.clear();
                }

            }
        });


        searchButton = new Button("Zoek");
        searchButton.setOnAction(e -> searchWithBSN());
        //endregion


        table.getColumns().addAll(bsnCol, fullNameCol, addressCol, countryCol, dateOfBirthCol, zipCodeCol, phoneCol, mailCol);
        vBox.getChildren().addAll(addBsn, addFirstName, addLastName, addStreet, addHouseNumber, addCity, addZipCode, addDateOfBirth, addPhone, addMail, addButton, removeButton);
        vBox.setSpacing(5);

        borderPane.setLeft(vBox);
        borderPane.setCenter(table);
        borderPane.setPrefSize(1200, 600);
        borderPane.setPadding(new Insets(10, 20, 10, 20));
        borderPane.setMargin(vBox, new Insets(12, 12, 12, 12));
        borderPane.setMargin(gridPane, new Insets(12, 12, 12, 12));

        pane.getTabs().addAll(appointmentTab, employeeTab, customerTab, manageEmployeeTab);
        customerTab.setContent(borderPane);
        pane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(pane);
        scene.getStylesheets().addAll(AppointmentGUI.class.getResource("/Light.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    public void searchWithBSN() {

        if (patientBSN.getText().equals("")) {
            AlertBox.display("Foutmelding", "Geen medewerkersnummer ingevoerd");
        } else {
            Patient tempPatient = dataManager.patientManager.searchWithBSN(patientBSN.getText());

            if (tempPatient != null) {
                bsnLabel.setText(String.valueOf(tempPatient.getPatientBSN()));
                nameLabel.setText(tempPatient.getPatientFullName());
                adressLabel.setText(tempPatient.getPatientAddress());
                zipcodeLabel.setText(tempPatient.getPatientZipCode());
                cityLabel.setText(tempPatient.getPatientCity());
                dateOfBirthLabel.setText(String.valueOf(tempPatient.getPatientDateOfBirth()));
                phoneLabel.setText(tempPatient.getPatientPhone());
                emailLabel.setText(tempPatient.getPatientEmail());

            } else {
                AlertBox.display("Foutmelding", "Geen patient gevonden met BSN: " + patientBSN.getText());
            }
        }
    }
}