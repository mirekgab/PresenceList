
package pl.mirekgab.presencelist.schedule.schedulegroup;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import pl.mirekgab.presencelist.employee.Employee;


@Embeddable
public class ScheduleGroupPK implements Serializable {

    @Column(name="schedule_year")
    private int year;
    @Column(name="schedule_month")
    private int month;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id") 
    private Employee employee;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + year;
        hash = 31 * hash + month;
        hash = 31 * hash + employee.getId().intValue();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ScheduleGroupPK other = (ScheduleGroupPK) obj;
        if (this.year != other.year) {
            return false;
        }
        if (this.month != other.month) {
            return false;
        }
        return this.employee.getId().intValue()==other.employee.getId().intValue();
    }
    
    
    
}
