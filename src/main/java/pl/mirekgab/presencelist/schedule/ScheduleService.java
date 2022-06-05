package pl.mirekgab.presencelist.schedule;

import java.util.List;
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

}
