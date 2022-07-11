package pl.mirekgab.presencelist.schedule.schedulegroup;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ScheduleGroupService {

    private final ScheduleGroupRepository groupRepository;

    public ScheduleGroupService(ScheduleGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public ScheduleGroup findByEmployeeAndYearAndMonth(Long employeeId, int year, int month) {
        return groupRepository.findByEmployeeAndYearAndMonth(employeeId, year, month);
    }

    public List<ScheduleGroup> getEmployeeSchedule() {
        return groupRepository.findAll();
    }

}
