


package pl.mirekgab.presencelist.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mirekgab.presencelist.schedule.schedulegroup.ScheduleGroup;

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
        model.addAttribute("schedules", scheduleService.getEmployeeSchedule());
        return "hr/schedule/index.html";
    }
    
    @GetMapping("/show")
    public String show(Model model, @RequestParam(name="employee_id") Long employeeId, 
            @RequestParam(name="year") int year, 
            @RequestParam(name="month") int month) {
        ScheduleGroup schedule = scheduleService.findByEmployeeAndYearAndMonth(employeeId, year, month);
        model.addAttribute("schedule", schedule);
        model.addAttribute("schedule_days", scheduleService.generateMonth(year, month));
        return "hr/schedule/schedule.html";
    }
    
    @GetMapping("/new")
    public String newSchedule() {
        return "hr/schedule/new";
    }
    
}
