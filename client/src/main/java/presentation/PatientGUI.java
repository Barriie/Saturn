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
    private TableView<Appointment> table;
    private DataManager dataManager = new DataManager();

    private HBox hBox = new HBox();
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

    TableColumn numberCol = new TableColumn("Nummer");
    TableColumn datumCol = new TableColumn("Datum");
    TableColumn startTimeCol = new TableColumn("Van");
    TableColumn stopTimeCol = new TableColumn("Tot");
    TableColumn fysioCol = new TableColumn("Fysio");
    //endregion

    @Override
    public void start(Stage stage) throws Exception {
        table = new TableView<>();

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

        numberCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, Integer>("appointmentNumber"));
        datumCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, LocalDate>("appointmentDate"));
        startTimeCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, LocalDate>("appointmentStartTimeString"));
        stopTimeCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, LocalDate>("appointmentStopTimeString"));
        fysioCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, String>("appointmentFysioName"));
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

        patientBSN = new TextField();
        patientBSN.setPromptText("BSN");

        searchButton = new Button("Zoek");
        searchButton.setOnAction(e -> searchWithBSN());
        //endregion

        table.getColumns().addAll(numberCol, datumCol, startTimeCol, stopTimeCol, fysioCol);
        hBox.getChildren().addAll(patientBSN, searchButton);
        hBox.setSpacing(10);
        gridPane.addColumn(0, BSN, NAME, ADDRESS, ZIPCODE, CITY, DATEOFBIRTH, PHONE, EMAIL);
        gridPane.addColumn(1, bsnLabel, nameLabel, adressLabel, zipcodeLabel, cityLabel, dateOfBirthLabel, phoneLabel, emailLabel);
        vBox.getChildren().addAll(hBox, gridPane);


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
            int bsn = Integer.parseInt(patientBSN.getText());
            Patient tempPatient = dataManager.patientManager.searchWithBSN(bsn);

            if (tempPatient != null) {
                bsnLabel.setText(String.valueOf(tempPatient.getPatientBSN()));
                nameLabel.setText(tempPatient.getPatientFullName());
                adressLabel.setText(tempPatient.getPatientAddress());
                zipcodeLabel.setText(tempPatient.getPatientZipCode());
                cityLabel.setText(tempPatient.getPatientCity());
                dateOfBirthLabel.setText(String.valueOf(tempPatient.getPatientDateOfBirth()));
                phoneLabel.setText(tempPatient.getPatientPhone());
                emailLabel.setText(tempPatient.getPatientEmail());
                ObservableList<Appointment> data = dataManager.appointmentManager.searchWithPatientBSN(tempPatient.getPatientBSN());
                table.setItems(data);

                for (Appointment aData : data) {
                    int lenght = 0;
                    if (aData.getAppointmentNumber().toString().length() > lenght) {
                        lenght = aData.getAppointmentNumber().toString().length();
                        numberCol.setMinWidth(lenght * 8.5);
                    }
                }
                for (Appointment aData : data) {
                    int lenght = 0;
                    if (aData.getAppointmentDate().toString().length() > lenght) {
                        lenght = aData.getAppointmentDate().toString().length();
                        datumCol.setMinWidth(lenght * 8.5);
                    }
                }
                for (Appointment aData : data) {
                    int lenght = 0;
                    if (aData.getAppointmentStartTimeString().length() > lenght) {
                        lenght = aData.getAppointmentStartTimeString().length();
                        startTimeCol.setMinWidth(lenght * 8.5);
                    }
                }
                for (Appointment aData : data) {
                    int lenght = 0;
                    if (aData.getAppointmentStopTimeString().length() > lenght) {
                        lenght = aData.getAppointmentStopTimeString().length();
                        stopTimeCol.setMinWidth(lenght * 8.5);
                    }
                }
                for (Appointment aData : data) {
                    int lenght = 0;
                    if (aData.getAppointmentFysioName().length() > lenght) {
                        lenght = aData.getAppointmentFysioName().length();
                        fysioCol.setMinWidth(lenght * 8.5);
                    }
                }
            } else {
                AlertBox.display("Foutmelding", "Geen patient gevonden met BSN: " + bsn);
            }
        }
    }
}