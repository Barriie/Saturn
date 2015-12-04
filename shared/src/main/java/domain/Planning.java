package domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;

@XmlRootElement(name = "planning")
public class Planning {
	private int PlanningNr;
	private String bsn;
	private String name;
	private String treatment;
	private String date;
	private String time;
	private String therapist;


	public Planning(String bsn, String name, String treatment, String date, String time, String therapist) {
		this.bsn = bsn;
		this.name = name;
		this.treatment = treatment;
		this.date = date;
		this.time = time;
		this.therapist = therapist;
	}

	@XmlElement(name = "planningId")
	public int getPlanningNr() {
		return PlanningNr;
	}

	public void setPlanningNr(int planningNr) {
		PlanningNr = planningNr;
	}

	@XmlElement(name = "date")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@XmlElement(name = "time")
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBsn() {
		return bsn;
	}

	public void setBsn(String bsn) {
		this.bsn = bsn;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getTherapist() {
		return therapist;
	}

	public void setTherapist(String therapist) {
		this.therapist = therapist;
	}
}