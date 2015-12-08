package presentation;

import businesslogic.DataManager;
import domain.Employee;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Created by Barrie on 21-10-2015.
 */
public class EmployeeGUI extends Application {

    private TableView<Employee> table;
    private DataManager dataManager = new DataManager();
    private ValidateInput validate;

    private VBox vBox = new VBox();
    private BorderPane borderPane = new BorderPane();

    private TabPane pane;
    private Tab appointmentTab;
    private Tab employeeTab;
    private Tab patientTab;
    private Tab manageEmployeeTab;

    TableColumn employeeNrCol = new TableColumn("Nummer");
    TableColumn nameCol = new TableColumn("Naam");
    TableColumn functionCol = new TableColumn("Functie");
    TableColumn bsnCol = new TableColumn("Bsn");
    TableColumn countryCol = new TableColumn("Stad");
    TableColumn houseCol = new TableColumn("Land");
    TableColumn streetCol = new TableColumn("Huisnummer");
    TableColumn cityCol = new TableColumn("Straat");
    TableColumn addressCol = new TableColumn("Adres");
    TableColumn dateOfBirthCol = new TableColumn("Geboortedatum");
    TableColumn zipCodeCol = new TableColumn("Postcode");
    TableColumn phoneCol = new TableColumn("Telefoonnummer");
    TableColumn emailCol = new TableColumn("Email");

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
        appointmentTab = new Tab("Afspraak Maken");
        employeeTab = new Tab("Afspraken Zoeken");
        patientTab = new Tab("Overzicht Patienten");
        manageEmployeeTab = new Tab("Overzicht Werknemers");

        pane.getSelectionModel().select(manageEmployeeTab);

        pane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue.getText()) {
                case "Afspraak Maken":
                    AppointmentGUI guiAppointment = new AppointmentGUI();
                    try {
                        guiAppointment.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Afspraken Zoeken":
                    SearchGUI guiEmployee = new SearchGUI();
                    try {
                        guiEmployee.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Overzicht Patienten":
                    PatientGUI guiCustomer = new PatientGUI();
                    try {
                        guiCustomer.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Overzicht Werknemers":
                    break;
            }
        });
        //endregion

        //region Creating columns for the table
        employeeNrCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("employeeNr"));
        employeeNrCol.setCellFactory(cellFactory);

        employeeNrCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Employee, String>>() {
                    @Override
                    public void handle(CellEditEvent<Employee, String> t) {
                        if (validate.validateNumber(t.getNewValue())) {
                            Employee tempEmployee = dataManager.employeeManager.searchEmployeeWithNumber(t.getNewValue());

                            if (tempEmployee == null) {
                                t.getTableView().getItems().get(
                                        t.getTablePosition().getRow()).setEmployeeNr(t.getNewValue());
                                dataManager.Save();
                                table.getColumns().get(0).setVisible(false);
                                table.getColumns().get(0).setVisible(true);
                            } else {
                                AlertBox.display("Foutmelding", t.getNewValue() + " word al gebruikt als werknemersnummer");
                                table.getColumns().get(0).setVisible(false);
                                table.getColumns().get(0).setVisible(true);
                            }
                        } else {
                            AlertBox.display("Foutmelding", t.getNewValue() + " is geen geldig werknemersnummer");
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeeNr(t.getOldValue());
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        }
                    }
                }
        );
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("employeeName"));
        nameCol.setCellFactory(cellFactory);
        nameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Employee, String>>() {
                    @Override
                    public void handle(CellEditEvent<Employee, String> t) {
                        if (validate.validateName(t.getNewValue())) {
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeeName(t.getNewValue());
                            dataManager.Save();
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        } else {
                            AlertBox.display("Foutmelding", t.getNewValue() + " is geen geldige naam");
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeeName(t.getOldValue());
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        }
                    }
                }
        );
        functionCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("employeeFunction"));
        functionCol.setCellFactory(cellFactory);
        functionCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Employee, String>>() {
                    @Override
                    public void handle(CellEditEvent<Employee, String> t) {
                        if (validate.validateName(t.getNewValue())) {
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeeFunction(t.getNewValue());
                            dataManager.Save();
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        } else {
                            AlertBox.display("Foutmelding", t.getNewValue() + " is geen geldige functie");
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeeFunction(t.getOldValue());
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        }
                    }
                }
        );
        bsnCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("employeeBSN"));
        bsnCol.setCellFactory(cellFactory);
        bsnCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Employee, String>>() {
                    @Override
                    public void handle(CellEditEvent<Employee, String> t) {
                        if (validate.validateBSN(t.getNewValue())) {
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeeBSN(t.getNewValue());
                            dataManager.Save();
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        } else {
                            AlertBox.display("Foutmelding", t.getNewValue() + " is geen geldig burgerservicenummer");
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeeBSN(t.getOldValue());
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        }
                    }
                }
        );
        addressCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("employeeAddress"));
        addressCol.setCellFactory(cellFactory);
        addressCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Employee, String>>() {
                    @Override
                    public void handle(CellEditEvent<Employee, String> t) {
                    }
                }
        );
        countryCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("employeeCountry"));
        countryCol.setCellFactory(cellFactory);
        countryCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Employee, String>>() {
                    @Override
                    public void handle(CellEditEvent<Employee, String> t) {
                        if (validate.validateName(t.getNewValue())) {
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeeCountry(t.getNewValue());
                            dataManager.Save();
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        } else {
                            AlertBox.display("Foutmelding", t.getNewValue() + " is geen geldig land");
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeeCountry(t.getOldValue());
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        }
                    }
                }
        );
        dateOfBirthCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("employeeDateOfBirth"));
        dateOfBirthCol.setCellFactory(cellFactory);
        dateOfBirthCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Employee, String>>() {
                    @Override
                    public void handle(CellEditEvent<Employee, String> t) {
                        if (validate.validateDateOfBirth(t.getNewValue())) {
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeeDateOfBirth(t.getNewValue());
                            dataManager.Save();
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        } else {
                            AlertBox.display("Foutmelding", t.getNewValue() + " is geen geldige geboortedatum (Let op: U mag geen - gebruiken!");
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeeDateOfBirth(t.getOldValue());
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        }
                    }
                }
        );
        zipCodeCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("employeeZipCode"));
        zipCodeCol.setCellFactory(cellFactory);
        zipCodeCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Employee, String>>() {
                    @Override
                    public void handle(CellEditEvent<Employee, String> t) {
                        if (validate.validateZipCode(t.getNewValue())) {
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeeZipCode(t.getNewValue());
                            dataManager.Save();
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        } else {
                            AlertBox.display("Foutmelding", t.getNewValue() + " is geen geldige postcode");
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeeZipCode(t.getOldValue());
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        }
                    }
                }
        );
        phoneCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("employeePhone"));
        phoneCol.setCellFactory(cellFactory);
        phoneCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Employee, String>>() {
                    @Override
                    public void handle(CellEditEvent<Employee, String> t) {
                        if (validate.validateNumber(t.getNewValue())) {
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeePhone(t.getNewValue());
                            dataManager.Save();
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        } else {
                            AlertBox.display("Foutmelding", t.getNewValue() + " is geen geldig telefoonnummer \n(Let op: U mag geen - gebruiken!");
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmployeePhone(t.getOldValue());
                            table.getColumns().get(0).setVisible(false);
                            table.getColumns().get(0).setVisible(true);
                        }
                    }
                }
        );
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("employeeEmail"));
        emailCol.setCellFactory(cellFactory);
        emailCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Employee, String>>() {
                    @Override
                    public void handle(CellEditEvent<Employee, String> t) {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setEmployeeEmail(t.getNewValue());
                        dataManager.Save();
                        table.getColumns().get(0).setVisible(false);
                        table.getColumns().get(0).setVisible(true);
                    }
                }
        );
        //endregion

        //region Creating textfields
        final TextField addNr = new TextField();
        addNr.setPromptText("Nr");
        addNr.setMaxWidth(employeeNrCol.getPrefWidth());

        final TextField addName = new TextField();
        addName.setMaxWidth(nameCol.getPrefWidth());
        addName.setPromptText("Naam");

        final TextField addFunction = new TextField();
        addFunction.setMaxWidth(functionCol.getPrefWidth());
        addFunction.setPromptText("Functie");

        final TextField addBsn = new TextField();
        addBsn.setMaxWidth(bsnCol.getPrefWidth());
        addBsn.setPromptText("BSN");

        final TextField addStreet = new TextField();
        addStreet.setMaxWidth(streetCol.getPrefWidth());
        addStreet.setPromptText("Straat");

        final TextField addHouseNumber = new TextField();
        addHouseNumber.setMaxWidth(houseCol.getPrefWidth());
        addHouseNumber.setPromptText("Huisnummer");

        final TextField addZipCode = new TextField();
        addZipCode.setMaxWidth(zipCodeCol.getPrefWidth());
        addZipCode.setPromptText("Postcode");

        final TextField addCity = new TextField();
        addCity.setMaxWidth(cityCol.getPrefWidth());
        addCity.setPromptText("Stad");

        final TextField addCountry = new TextField();
        addCountry.setMaxWidth(countryCol.getPrefWidth());
        addCountry.setPromptText("Land");

        final TextField addDateOfBirth = new TextField();
        addDateOfBirth.setMaxWidth(addDateOfBirth.getPrefWidth());
        addDateOfBirth.setPromptText("Geboortedatum");

        final TextField addPhone = new TextField();
        addPhone.setMaxWidth(phoneCol.getPrefWidth());
        addPhone.setPromptText("Telefoonnummer");

        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");
        //endregion

        //region Creating buttons
        final Button deleteButton = new Button("Verwijder");
        deleteButton.setOnAction(e -> {
                    Employee tempEmployee = table.getSelectionModel().getSelectedItem();

                    if (ConfirmBox.display("Bevestiging", "Weet u zeker dat u " + tempEmployee.getEmployeeName() + " wilt verwijderen?")) {
                        {
                            if (dataManager.employeeManager.deleteEmployee(tempEmployee)) {
                                AlertBox.display("Bevestiging", "Medewerker " + tempEmployee.getEmployeeName() + " verwijderd");
                                dataManager.Save();
                            } else {
                                AlertBox.display("Error", "Medewerker " + tempEmployee.getEmployeeName() + " niet verwijderd");
                            }
                        }
                    }
                }
        );

        final Button addButton = new Button("Voeg toe");
        addButton.setOnAction(e -> {

                    Employee tempEmployee = dataManager.employeeManager.searchEmployeeWithNumber(addNr.getText());

                    if (addNr.getText().equals("")) {
                        AlertBox.display("Error", "Er is geen medewerkersnummer ingevuld");
                    } else if (!validate.validateNumber(addNr.getText())) {
                        AlertBox.display("Error", addNr.getText() + " is geen geldig medewerkersnummer");
                        addNr.clear();
                    } else if (tempEmployee != null) {
                        AlertBox.display("Error", addNr.getText() + " word al gebruikt als medewerkersnummer");
                        addNr.clear();
                    } else if (addName.getText().equals("")) {
                        AlertBox.display("Error", "Er is geen naam ingevuld");
                    } else if (!validate.validateName(addName.getText())) {
                        AlertBox.display("Error", addName.getText() + " is geen geldige naam");
                        addName.clear();
                    } else if (addFunction.getText().equals("")) {
                        AlertBox.display("Error", "Er is geen functie ingevuld");
                    } else if (!validate.validateName(addFunction.getText())) {
                        AlertBox.display("Error", addFunction.getText() + " is geen geldige functie");
                        addFunction.clear();
                    } else if (addBsn.getText().equals("")) {
                        AlertBox.display("Error", "Er is geen bsn ingevuld");
                    } else if (!validate.validateNumber(addBsn.getText())) {
                        AlertBox.display("Error", addBsn.getText() + " is geen geldig bsn");
                        addBsn.clear();
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
                    } else if (addEmail.getText().equals("")) {
                        AlertBox.display("Error", "Er is geen email ingevuld");
                    } else {
                        Employee newEmployee = new Employee(
                                addNr.getText(),
                                addName.getText(),
                                addFunction.getText(),
                                addBsn.getText(),
                                addCity.getText(),
                                "Nederland",
                                addStreet.getText(),
                                addHouseNumber.getText(),
                                addDateOfBirth.getText(),
                                addZipCode.getText(),
                                addPhone.getText(),
                                addEmail.getText()
                        );
                        if (dataManager.employeeManager.addEmployee(newEmployee)) {
                            if (newEmployee.getEmployeeName().equals("Mark van Turnhout")) {
                                AlertBox.display("Melding", "Potverdorie, wat is die " + newEmployee.getEmployeeName() + " toch ook een knapperd he?!\n En niet te vergeten hilarisch natuurlijk....!");
                            } else {
                                AlertBox.display("Bevestiging", "Medewerker " + newEmployee.getEmployeeName() + " toegevoegd");
                            }
                        } else {
                            AlertBox.display("Error", "Medewerker " + newEmployee.getEmployeeName() + " niet toegevoegd");
                        }
                        addNr.clear();
                        addName.clear();
                        addFunction.clear();
                        addBsn.clear();
                        addHouseNumber.clear();
                        addCity.clear();
                        addStreet.clear();
                        addDateOfBirth.clear();
                        addZipCode.clear();
                        addPhone.clear();
                        addEmail.clear();
                        dataManager.Save();
                    }
                }
        );
        //endregion

        //region Setting table width
        table.setItems(dataManager.employeeManager.getData());
        for (int i = 0; i < dataManager.employeeManager.getData().size(); i++) {
            int lenght = 0;
            if (lenght < dataManager.employeeManager.getData().get(i).getEmployeeNr().length()) {
                lenght = dataManager.employeeManager.getData().get(i).getEmployeeNr().length();
                employeeNrCol.setMinWidth(lenght * 8.5);
            }
        }
        for (int i = 0; i < dataManager.employeeManager.getData().size(); i++) {
            int lenght = 0;
            if (lenght < dataManager.employeeManager.getData().get(i).getEmployeeName().length()) {
                lenght = dataManager.employeeManager.getData().get(i).getEmployeeName().length();
                nameCol.setMinWidth(lenght * 8.5);
            }
        }
        for (int i = 0; i < dataManager.employeeManager.getData().size(); i++) {
            int lenght = 0;
            if (lenght < dataManager.employeeManager.getData().get(i).getEmployeeFunction().length()) {
                lenght = dataManager.employeeManager.getData().get(i).getEmployeeFunction().length();
                functionCol.setMinWidth(lenght * 8.5);
            }
        }
        for (int i = 0; i < dataManager.employeeManager.getData().size(); i++) {
            int lenght = 0;
            if (lenght < dataManager.employeeManager.getData().get(i).getEmployeeBSN().length()) {
                lenght = dataManager.employeeManager.getData().get(i).getEmployeeBSN().length();
                bsnCol.setMinWidth(lenght * 8.5);
            }
        }
        for (int i = 0; i < dataManager.employeeManager.getData().size(); i++) {
            int lenght = 0;
            if (lenght < dataManager.employeeManager.getData().get(i).getEmployeeCity().length()) {
                lenght = dataManager.employeeManager.getData().get(i).getEmployeeCity().length();
                cityCol.setMinWidth(lenght * 8.5);
            }
        }
        for (int i = 0; i < dataManager.employeeManager.getData().size(); i++) {
            int lenght = 0;
            if (lenght < dataManager.employeeManager.getData().get(i).getEmployeeAddress().length()) {
                lenght = dataManager.employeeManager.getData().get(i).getEmployeeAddress().length();
                addressCol.setMinWidth(lenght * 8.5);
            }
        }
        for (int i = 0; i < dataManager.employeeManager.getData().size(); i++) {
            int lenght = 0;
            if (lenght < dataManager.employeeManager.getData().get(i).getEmployeeDateOfBirth().length()) {
                lenght = dataManager.employeeManager.getData().get(i).getEmployeeDateOfBirth().length();
                if (lenght > dateOfBirthCol.getText().length()) {
                    dateOfBirthCol.setMinWidth(lenght * 8.5);
                }
            }
        }
        for (int i = 0; i < dataManager.employeeManager.getData().size(); i++) {
            int lenght = 0;
            if (lenght < dataManager.employeeManager.getData().get(i).getEmployeeZipCode().length()) {
                lenght = dataManager.employeeManager.getData().get(i).getEmployeeZipCode().length();
                zipCodeCol.setMinWidth(lenght * 8.5);
            }
        }
        for (int i = 0; i < dataManager.employeeManager.getData().size(); i++) {
            int lenght = 0;
            if (lenght < dataManager.employeeManager.getData().get(i).getEmployeePhone().length()) {
                lenght = dataManager.employeeManager.getData().get(i).getEmployeePhone().length();
                if (lenght > phoneCol.getText().length()) {
                    phoneCol.setMinWidth(lenght * 8.5);
                }
            }
        }
        for (int i = 0; i < dataManager.employeeManager.getData().size(); i++) {
            int lenght = 0;
            if (lenght < dataManager.employeeManager.getData().get(i).getEmployeeEmail().length()) {
                lenght = dataManager.employeeManager.getData().get(i).getEmployeeEmail().length();
                emailCol.setMinWidth(lenght * 8.5);
            }
        }
        //endregion

        table.getColumns()
                .addAll(employeeNrCol, nameCol, functionCol, bsnCol, cityCol, addressCol, dateOfBirthCol, zipCodeCol, phoneCol, emailCol);

        vBox.getChildren()
                .addAll(addNr, addName, addFunction, addBsn, addStreet, addHouseNumber, addCity, addZipCode, addDateOfBirth, addPhone, addEmail, addButton, deleteButton);
        vBox.setSpacing(5);

        borderPane.setLeft(vBox);
        borderPane.setCenter(table);
        borderPane.setPrefSize(1200, 600);
        borderPane.setPadding(new Insets(10, 20, 10, 20));
        borderPane.setMargin(vBox, new Insets(12, 12, 12, 12));
        borderPane.setMargin(table, new Insets(12, 12, 12, 12));

        pane.getTabs().addAll(appointmentTab, employeeTab, patientTab, manageEmployeeTab);
        manageEmployeeTab.setContent(borderPane);
        pane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(pane);
        scene.getStylesheets().addAll(AppointmentGUI.class.getResource("/Light.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
