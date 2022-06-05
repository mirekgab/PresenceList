package pl.mirekgab.presencelist.schedule.schedulegroup;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.annotation.Immutable;
import pl.mirekgab.presencelist.employee.Employee;

@Entity
@Immutable
@Table(name="schedules_group")
public class ScheduleGroup {

    @Id
    @EmbeddedId
    private ScheduleGroupPK id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)    
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ScheduleGroupPK getId() {
        return id;
    }

    public void setId(ScheduleGroupPK id) {
        this.id = id;
    }

    
    
}
