package pl.mirekgab.presencelist.schedule;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        List<Schedule> listSchedule = scheduleService.findByEmployeeAndYearAndMonth(employeeId, year, month);

        List<ScheduleMonthDto> collect = listSchedule.stream().map(e
                -> ScheduleMapper.mapToScheduleMonthDto(e)
        ).collect(Collectors.toList());

        model.addAttribute("schedule_days", collect);
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
    public String edit(Model model, @RequestParam(name = "id") Long id) {
        Schedule schedule = scheduleService.findById(id);
        model.addAttribute("schedule", ScheduleMapper.map(schedule));
        return "hr/schedule/edit.html";
    }

    @PostMapping("/generate")
    public String generate(Long employeeId, int year, int month) {
        if (scheduleService.scheduleExists(employeeId, year, month)) {
            return "/hr/schedule/schedule_exists.html";
        } else {
            scheduleService.generateSchedule(employeeId, year, month);
            return "redirect:/hr/schedule/index";
        }
    }

    @GetMapping("/save")
    public String save(ScheduleDto schedule) {
        scheduleService.save(schedule);
        return "redirect:/hr/schedule/show?employee_id=" + schedule.getEmployeeId() + "&year=" + schedule.getYear() + "&month=" + schedule.getMonth();
    }

    @GetMapping("/delete")
    public String delete(Model model,
            @RequestParam(name = "employee_id") Long employeeId,
            @RequestParam(name = "year") int year,
            @RequestParam(name = "month") int month) {
        scheduleService.delete(employeeId, year, month);
        return "redirect:/hr/schedule/index";
    }
}
