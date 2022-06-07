
package pl.mirekgab.presencelist.schedule.schedulegroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ScheduleGroupRepository extends JpaRepository<ScheduleGroup, Long> {

    @Query(nativeQuery = false, value = "select s from ScheduleGroup s where employee_id=:employeeId and schedule_year=:year and schedule_month=:month")
    public ScheduleGroup findByEmployeeAndYearAndMonth(Long employeeId, int year, int month);

}
