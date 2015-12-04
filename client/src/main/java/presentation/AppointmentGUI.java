package presentation;

import businesslogic.AppointmentManager;
import businesslogic.DataManager;
import businesslogic.EmployeeManager;
import businesslogic.PatientManager;
import domain.*;
import javafx.application.Application;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentGUI extends Application {
    //region Attributes and properties
    private TableView<Appointment> table;
    private DataManager dataManager = new DataManager();
    private ValidateInput validate;

    private TabPane pane;
    private Tab appointmentTab;
    private Tab employeeTab;
    private Tab patientTab;
    private Tab manageEmployeeTab;

    private VBox vBox = new VBox();
    private BorderPane borderPane = new BorderPane();
    TableColumn<Appointment, String> numberCol = new TableColumn<Appointment, String>("Nummer");
    TableColumn startTimeCol = new TableColumn("Van");
    TableColumn stopTimeCol = new TableColumn("Tot");
    TableColumn fysioCol = new TableColumn("Fysio");
    TableColumn patientCol = new TableColumn("Patient");
    //endregion

    @Override
    public void start(Stage stage) throws Exception {
        table = new TableView<>();
        validate = new ValidateInput();

        stage.setTitle("Fysio App");

        table.setEditable(true);

        //region Creating tabs
        pane = new TabPane();
        appointmentTab = new Tab("Afspraak");
        employeeTab = new Tab("Medewerker");
        patientTab = new Tab("Patient");
        manageEmployeeTab = new Tab("Overzicht Werknemers");

        pane.getSelectionModel().select(appointmentTab);

        //adding action listeners
        pane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue.getText()) {
                case "Afspraak":
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
                    PatientGUI guiCustomer = new PatientGUI();
                    try {
                        dataManager.Save();
                        guiCustomer.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Overzicht Werknemers":
                    ManageEmployeeGUI guiManageEmploye = new ManageEmployeeGUI();
                    try {
                        dataManager.Save();
                        guiManageEmploye.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        });
        //endregion

        //region Creating columns for table

        numberCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, String>("appointmentNumber"));



        startTimeCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, LocalDate>("appointmentStartTimeString"));

        startTimeCol.setOnEditCommit(
                new EventHandler<CellEditEvent>() {
                    @Override
                    public void handle(CellEditEvent event) {

                    }
                }
        );


        stopTimeCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, LocalDate>("appointmentStopTimeString"));

        stopTimeCol.setOnEditCommit(
                new EventHandler<CellEditEvent>() {
                    @Override
                    public void handle(CellEditEvent event) {

                    }
                }
        );


        fysioCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, String>("appointmentFysioName"));

        fysioCol.setOnEditCommit(
                new EventHandler<CellEditEvent>() {
                    @Override
                    public void handle(CellEditEvent event) {

                    }
                }
        );


        patientCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, String>("appointmentPatientName"));
        patientCol.setOnEditCommit(
                new EventHandler<CellEditEvent>() {
                    @Override
                    public void handle(CellEditEvent event) {

                    }
                }
        );
        //endregion

        //region Refreshing of the list
        FilteredList<Appointment> filteredData = new FilteredList<>(dataManager.appointmentManager.getData());
        SortedList<Appointment> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

        for (Appointment aSortedData : sortedData) {
            int length = 0;
            if (aSortedData.getAppointmentStartTimeString().length() > length) {
                length = aSortedData.getAppointmentStartTimeString().toString().length();
                startTimeCol.setMinWidth(length*8.5);
            }

        }

        for (Appointment aSortedData : sortedData) {
            int length = 0;
            if (aSortedData.getAppointmentNumber().toString().length() > length) {
                length = aSortedData.getAppointmentNumber().toString().length();
                numberCol.setMinWidth(length*8.5);
            }
        }

        for (Appointment aSortedData : sortedData) {
            int length = 0;
            if (aSortedData.getAppointmentStopTimeString().toString().length() > length) {
                length = aSortedData.getAppointmentStopTimeString().toString().length();
                stopTimeCol.setMinWidth(length*8.5);
            }
        }

        for (Appointment aSortedData : sortedData) {
            int length = 0;
            if (aSortedData.getAppointmentFysioName().length() > length) {
                length = aSortedData.getAppointmentFysioName().length();
                fysioCol.setMinWidth(length*8.5);
            }
        }

        for (Appointment aSortedData : sortedData) {
            int length = 0;
            if (aSortedData.getAppointmentPatientName().length() > length) {
                length = aSortedData.getAppointmentPatientName().length();
                patientCol.setMinWidth(length*8.5);
            }
        }

        //endregion

        //region Creating Textfields
        DatePicker dp_AppointmentDate = new DatePicker();
        dp_AppointmentDate.valueProperty().addListener((observable, oldValue, newValue) -> {});
        dp_AppointmentDate.setPromptText("Kies datum");
        dp_AppointmentDate.setValue(LocalDate.now());

        TextField appointmentNumber = new TextField();
        appointmentNumber.setPromptText("Nr");

        TextField appointmentStartTime = new TextField();
        appointmentStartTime.setPromptText("Van");

        TextField appointmentStopTime = new TextField();
        appointmentStopTime.setPromptText("Tot");

        ComboBox<String> appointmentFysio = new ComboBox<>(dataManager.employeeManager.getEmployeeNames());
        appointmentFysio.setPromptText("Fysio");

        ComboBox<String> appointmentPatient = new ComboBox<>(dataManager.patientManager.getPatientNames());
        appointmentPatient.setPromptText("Patient");

        Label lbl_Afspraak = new Label("Nieuwe Afspraak: ");
        //endregion

        //region Creating buttons
        Button removeAppointment = new Button("Verwijder");
        removeAppointment.setOnAction(e -> {
            Appointment tempAppointment = table.getSelectionModel().getSelectedItem();

            if (ConfirmBox.display("Bevestiging", "Weet u zeker dat u afspraak: " + tempAppointment.getAppointmentNumber() + " wilt verwijderen?")) {
                {
                    if (dataManager.appointmentManager.deleteAppointment(tempAppointment)) {
                        AlertBox.display("Bevestiging", "Afspraak " + tempAppointment.getAppointmentNumber() + " verwijderd");
                    } else {
                        AlertBox.display("Error", "Afspraak  " + tempAppointment.getAppointmentNumber() + " niet verwijderd");
                    }
                }
            }
        });

        Button addAppointment = new Button("Voeg toe");
        addAppointment.setOnAction(e -> {
            Appointment tempAppointment = null;

            if (appointmentNumber.getText().equals("")) {
                AlertBox.display("Foutmelding", "Geen afspraaknummer ingevoerd");
            } else {
                tempAppointment = dataManager.appointmentManager.searchWithAppointmentNumber(appointmentNumber.getText());

                if (tempAppointment != null) {
                    AlertBox.display("Foutmelding", "Er is al een afspraak met dit nummer: " + tempAppointment.getAppointmentNumber());
                } else if (!validate.validateNumber(appointmentNumber.getText())) {
                    AlertBox.display("Error", appointmentNumber.getText() + " is geen geldig afspraaksnummer");
                } else if (dp_AppointmentDate.getValue().equals("")) {
                    AlertBox.display("Foutmelding", "Geen datum gekozen");
                } else if (appointmentStartTime.getText().equals("")) {
                    AlertBox.display("Foutmelding", "Geen start tijd ingevoerd");
                } else if (appointmentStopTime.getText().equals("")) {
                    AlertBox.display("Foutmelding", "Geen stop tijd ingevoerd");
                } else if (appointmentFysio.getValue() == null) {
                    AlertBox.display("Foutmelding", "Geen fysio gekozen");
                } else if (appointmentPatient.getValue() == null) {
                    AlertBox.display("Foutmelding", "Geen patient gekozen");
                } else {
                    if (!validate.validateTime(appointmentStartTime.getText())) {
                        AlertBox.display("Foutmelding", "Tijd moet in mm:hh, bv: 13:14");
                    } else if (!validate.validateTime(appointmentStopTime.getText())) {

                        AlertBox.display("Foutmelding", "Tijd moet in mm:hh, bv: 13:14");
                    } else {
                        LocalTime newStartTime = LocalTime.parse(appointmentStartTime.getText());
                        LocalTime newStopTime = LocalTime.parse(appointmentStopTime.getText());
                        String newAppointmentNumber = appointmentNumber.getText();
                        LocalDate newAppointmentDate = dp_AppointmentDate.getValue();
                        Employee newFysio = dataManager.employeeManager.searchEmployeeWithName(String.valueOf(appointmentFysio.getValue()));
                        Patient newPatient = dataManager.patientManager.searchPatientWithName(String.valueOf(appointmentPatient.getValue()));

                        Appointment newAppointment = new Appointment(newAppointmentNumber, newAppointmentDate, newStartTime, newStopTime, newFysio, newPatient);
                        dataManager.appointmentManager.addApointment(newAppointment);

                        Workday workday = new Workday(newAppointmentDate, newStartTime, newStopTime);
                        newFysio.addWorkday(workday);
                    }
                }
            }
        });
        //endregion

        table.getColumns().addAll(numberCol, startTimeCol, stopTimeCol, fysioCol, patientCol);
        vBox.getChildren().addAll(dp_AppointmentDate, lbl_Afspraak, appointmentNumber, appointmentStartTime, appointmentStopTime, appointmentFysio, appointmentPatient, addAppointment, removeAppointment);
        vBox.setSpacing(5);

        borderPane.setLeft(vBox);
        borderPane.setCenter(table);
        borderPane.setPrefSize(1200, 600);
        borderPane.setPadding(new Insets(10, 20, 10, 20));
        borderPane.setMargin(vBox, new Insets(12, 12, 12, 12));
        borderPane.setMargin(table, new Insets(12, 12, 12, 12));

        pane.getTabs().addAll(appointmentTab, employeeTab, patientTab, manageEmployeeTab);
        appointmentTab.setContent(borderPane);
        pane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(pane);
        scene.getStylesheets().addAll(AppointmentGUI.class.getResource("/Light.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.getIcons().add(new Image("/fysio_icon1.png"));
    }
}