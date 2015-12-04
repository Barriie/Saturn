package domain;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "treatment")
public class Treatment {
	//region Attributes and properties
	private SimpleIntegerProperty treatmentCode = new SimpleIntegerProperty();
	private SimpleStringProperty treatmentName = new SimpleStringProperty();
	private SimpleIntegerProperty treatmentSessions = new SimpleIntegerProperty();
	private SimpleDoubleProperty treatmentSessionCost = new SimpleDoubleProperty();
	private SimpleDoubleProperty treatmentSessionDuration = new SimpleDoubleProperty();

	//endregion

	public Treatment() {

	}

	//region Methods
	public Treatment (int code, String name, int amountSessions, double sessionCost, double sessionDuration) {
		this.treatmentCode = new SimpleIntegerProperty(this, "treatmentCode", code);
		this.treatmentName = new SimpleStringProperty(this, "treatmentName", name);
		this.treatmentSessions = new SimpleIntegerProperty(this, "treatmentSessions", amountSessions);
		this.treatmentSessionCost = new SimpleDoubleProperty(this, "treatmentSessionCost", sessionCost);
		this.treatmentSessionDuration = new SimpleDoubleProperty(this, "treatmentSessionDuration", sessionDuration);
	}
	//endregion

	//region Getters and setters

	@XmlElement(name = "code")
	public int getTreatmentCode() {
		return treatmentCode.get();
	}

	public SimpleIntegerProperty treatmentCodeProperty() {
		return treatmentCode;
	}
	public void setTreatmentCode(int treatmentCode) {
		this.treatmentCode.set(treatmentCode);
	}

	@XmlElement(name = "name")
	public String getTreatmentName() {
		return treatmentName.get();
	}

	public SimpleStringProperty treatmentNameProperty() {
		return treatmentName;
	}

	public void setTreatmentName(String treatmentName) {
		this.treatmentName.set(treatmentName);
	}

	@XmlElement(name = "numberOfSessions")
	public int getTreatmentSessions() {
		return treatmentSessions.get();
	}

	public SimpleIntegerProperty treatmentSessionsProperty() {
		return treatmentSessions;
	}

	public void setTreatmentSessions(int treatmentSessions) {
		this.treatmentSessions.set(treatmentSessions);
	}

	@XmlElement(name = "priceTreatment")
	public double getTreatmentSessionCost() {
		return treatmentSessionCost.get();
	}

	public SimpleDoubleProperty treatmentSessionCostProperty() {
		return treatmentSessionCost;
	}

	public void setTreatmentSessionCost(double treatmentSessionCost) {
		this.treatmentSessionCost.set(treatmentSessionCost);
	}

	@XmlElement(name = "sessionDuration")
	public double getTreatmentSessionDuration() {
		return treatmentSessionDuration.get();
	}

	public SimpleDoubleProperty treatmentSessionDurationProperty() {
		return treatmentSessionDuration;
	}

	public void setTreatmentSessionDuration(double treatmentSessionDuration) {
		this.treatmentSessionDuration.set(treatmentSessionDuration);
	}
	//endregion
}