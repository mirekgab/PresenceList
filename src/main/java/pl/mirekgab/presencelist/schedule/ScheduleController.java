


package pl.mirekgab.presencelist.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hr/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    
    

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("schedules", scheduleService.getAll());
        return "hr/schedule/index.html";
    }
    
    @GetMapping("/show")
    public String show(Model model, @RequestParam(name="schedule_id") Long scheduleId) {
        Schedule schedule = scheduleService.findById(scheduleId);
        model.addAttribute("schedule", schedule);
        return "hr/schedule/schedule.html";
    }
}
