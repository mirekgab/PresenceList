package pl.mirekgab.presencelist.schedule;

import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import pl.mirekgab.presencelist.employee.Employee;

@Entity
@Table(name="schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="schedule_year")
    private int year;
    
    @Column(name="schedule_month")
    private int month;
    
    @Column(name="schedule_day")
    private int day;
    
    @Column(name="start_of_work")
    private int startOfWork;
    
    @Column(name="end_of_work")
    private int endOfWork;
    
    @Column(name="time_of_work")
    private int timeOfWork;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getStartOfWork() {
        return startOfWork;
    }

    public void setStartOfWork(int startOfWork) {
        this.startOfWork = startOfWork;
    }

    public int getEndOfWork() {
        return endOfWork;
    }

    public void setEndOfWork(int endOfWork) {
        this.endOfWork = endOfWork;
    }

    public int getTimeOfWork() {
        return timeOfWork;
    }

    public void setTimeOfWork(int timeOfWork) {
        this.timeOfWork = timeOfWork;
    }
    
    public String stringStartOfWork() {
        return LocalTime.ofSecondOfDay(startOfWork).toString();
    }
    
    public String stringEndOfWork() {
        return LocalTime.ofSecondOfDay(endOfWork).toString();
    }
    public String stringTimeOfWork() {
        return LocalTime.ofSecondOfDay(timeOfWork).toString();
    }      
    
}
