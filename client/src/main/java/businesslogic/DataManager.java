package businesslogic;

/**
 * Created by Barrie on 4-12-2015.
 */
public class DataManager {

    public AppointmentManager appointmentManager;
    public EmployeeManager employeeManager;
    public PatientManager patientManager;
    public TreatmentManager treatmentManager;

    public DataManager()
    {
        this.employeeManager = new EmployeeManager();
        this.patientManager = new PatientManager();
        this.treatmentManager = new TreatmentManager();
        this.appointmentManager = new AppointmentManager();
    }

    public void Save()
    {
        employeeManager.Save();
        patientManager.Save();
        treatmentManager.Save();
        appointmentManager.Save();
    }

    public void Load()
    {
        employeeManager.Load();
        patientManager.Load();
        treatmentManager.Load();
        appointmentManager.Load();
    }


}
