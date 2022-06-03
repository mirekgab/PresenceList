package pl.mirekgab.presencelist.schedule;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final ScheduleRepository repository;

    @Autowired
    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }

    public List<Schedule> getAll() {
        return repository.groupByEmployees();
    }

    public Schedule findById(Long scheduleId) {
        return repository.findById(scheduleId).orElseThrow();
    }

}
