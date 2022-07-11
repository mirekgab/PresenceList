package pl.mirekgab.presencelist.schedule;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mirekgab.presencelist.MonthsName;
import pl.mirekgab.presencelist.employee.Employee;
import pl.mirekgab.presencelist.employee.EmployeeService;
import pl.mirekgab.presencelist.schedule.schedulegroup.ScheduleGroup;
import pl.mirekgab.presencelist.schedule.schedulegroup.ScheduleGroupService;

@Controller
@RequestMapping("/hr/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final EmployeeService employeeService;
    private final ScheduleGroupService scheduleGroupService;

    @Autowired
    public ScheduleController(
            ScheduleService scheduleService,
            EmployeeService employeeService,
            ScheduleGroupService scheduleGroupService) {
        this.scheduleService = scheduleService;
        this.employeeService = employeeService;
        this.scheduleGroupService = scheduleGroupService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("schedules", scheduleGroupService.getEmployeeSchedule());
        return "hr/schedule/index.html";
    }

    @GetMapping("/show")
    public String show(Model model, @RequestParam(name = "employee_id") Long employeeId,
            @RequestParam(name = "year") int year,
            @RequestParam(name = "month") int month) {
        ScheduleGroup schedule = scheduleGroupService.findByEmployeeAndYearAndMonth(employeeId, year, month);
        model.addAttribute("schedule", schedule);

        List<ScheduleMonthDto> list = new LinkedList<>();
        model.addAttribute("schedule_days", list);
        return "hr/schedule/show.html";
    }

    @GetMapping("/new")
    public String newSchedule(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);

        List<Month> months = new LinkedList<>();
        for (int a = 1; a <= 12; a++) {
            months.add(LocalDate.of(LocalDate.now().getYear(), a, 1).getMonth());
        }
        model.addAttribute("months", months);
        model.addAttribute("monthsName", MonthsName.months);
        model.addAttribute("year", LocalDate.now().getYear());
        return "hr/schedule/new.html";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name = "employee_id") Long employeeId, int year, int month, int day) {
        Schedule schedule = scheduleService.findByEmployeeAndYearAndMonthAndDay(employeeId, year, month, day);
        System.out.println(schedule);
        model.addAttribute("schedule", ScheduleMapper.map(schedule));
        return "hr/schedule/edit.html";
    }

    @PostMapping("/generate")
    public String generate(Long employeeId, int year, int month) {
        scheduleService.generateSchedule(employeeId, year, month);

        return "redirect:/hr/schedule/index";
    }

    @GetMapping("/save")
    public String save(ScheduleDto schedule) {
        scheduleService.save(ScheduleMapper.map(schedule));
        return "redirect:/hr/schedule/show?employee_id=" + schedule.getEmployeeId() + "&year=" + schedule.getYear() + "&month=" + schedule.getMonth();
    }
}
