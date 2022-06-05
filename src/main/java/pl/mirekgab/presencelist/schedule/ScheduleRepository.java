/*
 */
package pl.mirekgab.presencelist.schedule;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mirekgab.presencelist.employee.Employee;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(nativeQuery = false, value = "select s from Schedule s")
    public List<Schedule> getEmployeeSchedule();


    
}
