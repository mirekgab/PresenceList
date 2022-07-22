/*
 */
package pl.mirekgab.presencelist.schedule;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mirekgab.presencelist.department.Department;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(nativeQuery = false, value = "select s from Schedule s")
    public List<Schedule> getEmployeeSchedule();

    public Schedule findByEmployeeIdAndYearAndMonthAndDay(Long employeeId, int year, int month, int day);
    public List<Schedule> findByEmployeeIdAndYearAndMonth(Long employeeId, int year, int month);

    //@Query(nativeQuery=false, value="select s from Schedule s where s.year=:year and s.month=:month")
   // public List<Schedule> findByDepartmentAndYearAndMonth(Department department, int year, int month);
    
    
    @Modifying
    @Transactional(Transactional.TxType.MANDATORY)
    @Query(nativeQuery=false, value="delete from Schedule where employee_id=:employeeId and year=:year and month=:month")
    public void deleteByEmployeeAndYearAndMonth(Long employeeId, int year, int month);

    @Query("select case when count(s)>0 then true else false end from Schedule s join s.employee e where e.id=:id and s.year=:year and s.month=:month")
    public boolean existsByEmployeeAndYearAndMonth(Long id, int year, int month);

    /**
     * 
     * @param employeeId
     * @param year
     * @param month
     * @return sum time of work in seconds
     */
    @Query("select sum(timeOfWork) from Schedule s where working_day=1 and employee_id=:employeeId and year=:year and month=:month")
    public int totalWorkingTime(Long employeeId, int year, int month);
    
}
