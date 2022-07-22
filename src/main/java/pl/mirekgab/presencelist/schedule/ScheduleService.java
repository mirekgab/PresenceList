package pl.mirekgab.presencelist.schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mirekgab.presencelist.department.Department;
import pl.mirekgab.presencelist.employee.EmployeeRepository;

@Service
public class ScheduleService {

    private final ScheduleRepository repository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public ScheduleService(ScheduleRepository repository, EmployeeRepository employeeRepository) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
    }

    public List<Schedule> getAll() {
        return repository.findAll();
    }

    public Schedule findById(Long scheduleId) {
        return repository.findById(scheduleId).orElseThrow();
    }

    Schedule findByEmployeeAndYearAndMonthAndDay(Long employeeId, int year, int month, int day) {
        return repository.findByEmployeeIdAndYearAndMonthAndDay(employeeId, year, month, day);
    }

    public List<Schedule> findByEmployeeAndYearAndMonth(Long employeeId, int year, int month) {
        return repository.findByEmployeeIdAndYearAndMonth(employeeId, year, month);
    }

    void generateSchedule(Long employeeId, int year, int month) {
        List<Schedule> list = new LinkedList<>();
        List<ScheduleMonthDto> listMonth = generateMonth(year, month);
        listMonth.stream().forEach(e -> {
            list.add(createSchedule(employeeId, year, month, e));
        });
        repository.saveAllAndFlush(list);
    }

    private List<ScheduleMonthDto> generateMonth(int year, int month) {
        List<ScheduleMonthDto> list = new ArrayList<>();
        LocalDate localDate = LocalDate.of(year, month, 1);
        for (int a = 1; a <= localDate.lengthOfMonth(); a++) {
            ScheduleMonthDto s = new ScheduleMonthDto();
            s.setDay(a);
            s.setDayOfWeek(LocalDate.of(year, month, a).getDayOfWeek().getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()));
            if (s.getDayOfWeek().equals("sobota") || s.getDayOfWeek().equals("niedziela")) {
                s.setWorkingDay(false);
            } else {
                s.setWorkingDay(true);
            }
            s.setStartOfWork(LocalTime.of(7, 0).toSecondOfDay());
            s.setEndOfWork(LocalTime.of(15, 0).toSecondOfDay());
            s.setTimeOfWork(s.getEndOfWork() - s.getStartOfWork());
            list.add(s);
        }
        return list;
    }

    private Schedule createSchedule(Long employeeId, int year, int month, ScheduleMonthDto e) {
        Schedule schedule = new Schedule();
        schedule.setEmployee(employeeRepository.findById(employeeId).get());
        schedule.setYear(year);
        schedule.setMonth(month);
        schedule.setDay(e.getDay());
        schedule.setStartOfWork(e.getStartOfWork());
        schedule.setEndOfWork(e.getEndOfWork());
        schedule.setTimeOfWork(e.getTimeOfWork());
        schedule.setWorkingDay(e.getWorkingDay() ? 1 : 0);
        return schedule;
    }

    public void save(ScheduleDto schedule) {
        Schedule s = repository.findById(schedule.getId()).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        s.setStartOfWork(LocalTime.parse(schedule.getStartOfWork(), formatter).toSecondOfDay());
        s.setEndOfWork(LocalTime.parse(schedule.getEndOfWork(), formatter).toSecondOfDay());
        s.setTimeOfWork(s.getEndOfWork() - s.getStartOfWork());
        s.setWorkingDay(schedule.getWorkingDay() ? 1 : 0);



        repository.save(s);
    }

    @Transactional
    public void delete(Long employeeId, int year, int month) {
        repository.deleteByEmployeeAndYearAndMonth(employeeId, year, month);
    }

    boolean scheduleExists(Long employeeId, int year, int month) {
        return !findByEmployeeAndYearAndMonth(employeeId, year, month).isEmpty();
    }

    public List<Schedule> findByDepartmentAndYearAndMonth(Department department, int year, int month) {
        //return repository.findByDepartmentAndYearAndMonth(department, year, month);
        return null;
    }
    
    public boolean existsByEmployeeAndYearAndMonth(Long id, int year, int month) {
        return repository.existsByEmployeeAndYearAndMonth(id, year, month);
    }

    /**
     * 
     * @param employeeId
     * @param year
     * @param month
     * @return total working time in hour
     */
    public int totalWorkingTime(Long employeeId, int year, int month) {
        int timeInSeconds = repository.totalWorkingTime(employeeId, year, month);
        return timeInSeconds/60/60;
    }

}
