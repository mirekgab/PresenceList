package pl.mirekgab.presencelist.schedule.schedulegroup;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
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

    
    public String getMonthName() {
        String monthName = LocalDate.of(1, this.id.getMonth(), 1).getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault());
        return monthName;
    }
}
