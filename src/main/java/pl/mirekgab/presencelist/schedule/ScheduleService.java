package pl.mirekgab.presencelist.schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mirekgab.presencelist.schedule.schedulegroup.ScheduleGroup;
import pl.mirekgab.presencelist.schedule.schedulegroup.ScheduleGroupRepository;

@Service
public class ScheduleService {

    private final ScheduleRepository repository;
    private final ScheduleGroupRepository groupRepository;

    @Autowired
    public ScheduleService(ScheduleRepository repository, ScheduleGroupRepository groupRepository) {
        this.repository = repository;
        this.groupRepository = groupRepository;
    }


    public List<Schedule> getAll() {
        return repository.findAll();
    }


    public Schedule findById(Long scheduleId) {
        return repository.findById(scheduleId).orElseThrow();
    }

    public List<ScheduleGroup> getEmployeeSchedule() {
        return groupRepository.findAll();
    }

    ScheduleGroup findByEmployeeAndYearAndMonth(Long employeeId, int year, int month) {
        return groupRepository.findByEmployeeAndYearAndMonth(employeeId, year, month);
    }

    List<ScheduleMonthDto> generateMonth(int year, int month) {
        List<ScheduleMonthDto> list = new ArrayList<>();
        LocalDate localDate = LocalDate.of(year, month, 1);
        for (int a=1;a<=localDate.lengthOfMonth();a++) {
            ScheduleMonthDto s = new ScheduleMonthDto();
            s.setDay(a);
            s.setDayOfWeek(LocalDate.of(year, month, a).getDayOfWeek().getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()));
            s.setStartOfWork(LocalTime.of(7, 0).toSecondOfDay());
            s.setEndOfWork(LocalTime.of(15, 0).toSecondOfDay());
            s.setTimeOfWork(s.getEndOfWork()-s.getStartOfWork());
            list.add(s);
        }
        return list;
    }

}
