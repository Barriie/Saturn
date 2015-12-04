package domain;

import datastorage.DateAdapter;
import datastorage.TimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Barrie on 31-10-2015.
 */
@XmlRootElement(name = "Workday")
public class Workday {
    //region Attributes and properties
    private LocalDate workDate;
    private LocalTime startTime;
    private LocalTime stopTime;
    //endregion

    public Workday(){

    }

    //region Methods
    public Workday(LocalDate workDate, LocalTime startTime, LocalTime stopTime) {
        this.workDate = workDate;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }
    //endregion

    //region Getters and setters

    @XmlElement(name = "workDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public LocalDate getWorkDate() {
        return workDate;
    }

    @XmlElement(name = "startTime")
    @XmlJavaTypeAdapter(TimeAdapter.class)
    public LocalTime getStartTime() {
        return startTime;
    }

    @XmlElement(name = "stopTime")
    @XmlJavaTypeAdapter(TimeAdapter.class)
    public LocalTime getStopTime() {
        return stopTime;
    }
    //endregion
}
