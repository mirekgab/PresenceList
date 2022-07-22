package pl.mirekgab.presencelist.manager;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mirekgab.presencelist.MonthsName;
import pl.mirekgab.presencelist.department.Department;
import pl.mirekgab.presencelist.department.DepartmentService;
import pl.mirekgab.presencelist.employee.Employee;
import pl.mirekgab.presencelist.employee.EmployeeService;
import pl.mirekgab.presencelist.schedule.Schedule;
import pl.mirekgab.presencelist.schedule.ScheduleDto;
import pl.mirekgab.presencelist.schedule.ScheduleMapper;
import pl.mirekgab.presencelist.schedule.ScheduleMonthDto;
import pl.mirekgab.presencelist.schedule.ScheduleService;
import pl.mirekgab.presencelist.schedule.schedulegroup.ScheduleGroup;
import pl.mirekgab.presencelist.schedule.schedulegroup.ScheduleGroupService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private static final Logger LOG = Logger.getLogger(ManagerController.class.getName());

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final ScheduleService scheduleService;
    private final ScheduleGroupService scheduleGroupService;

    @Autowired
    public ManagerController(DepartmentService departmentService,
            EmployeeService employeeService,
            ScheduleService scheduleService,
            ScheduleGroupService scheduleGroupService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.scheduleService = scheduleService;
        this.scheduleGroupService = scheduleGroupService;
    }

    @GetMapping("/index")
    public String start(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("years", Arrays.asList(2022, 2021, 2020));
        model.addAttribute("months", MonthsName.months);
        return "manager/index.html";
    }

    @GetMapping("/department")
    public String department(Model model, Long departmentId, int year, int month) {
        LOG.info(String.valueOf(departmentId + " " + year + " " + month));
        Department department = departmentService.findById(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("month", MonthsName.months[month]);
        model.addAttribute("monthId", month);
        model.addAttribute("year", year);

        List<Employee> employees = employeeService.findByDepartmentId(departmentId);
        List<Employee> employeesWithSchedule = new LinkedList<>();
        employees.stream().forEach(e -> {
            //check if employee has a schedule for the selected year and the selected month
            if (scheduleService.existsByEmployeeAndYearAndMonth(e.getId(), year, month)) {
                employeesWithSchedule.add(e);
            }
        });
        model.addAttribute("employees", employeesWithSchedule);

        return "manager/department.html";
    }
    
    @GetMapping("/show")
    public String show(Model model, @RequestParam(name = "employee_id") Long employeeId, int year, int month) {
        
        ScheduleGroup schedule = scheduleGroupService.findByEmployeeAndYearAndMonth(employeeId, year, month);
        model.addAttribute("schedule", schedule);

        model.addAttribute("departmentId", schedule.getEmployee().getDepartment().getId());
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("totalWorkingTime", scheduleService.totalWorkingTime(employeeId, year, month));
        
                
        List<ScheduleMonthDto> list = new LinkedList<>();
        List<Schedule> listSchedule = scheduleService.findByEmployeeAndYearAndMonth(employeeId, year, month);

        List<ScheduleMonthDto> collect = listSchedule.stream().map(e
                -> ScheduleMapper.mapToScheduleMonthDto(e)
        ).collect(Collectors.toList());

        model.addAttribute("schedule_days", collect);        
        return "/manager/show";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name = "id") Long id) {
        Schedule schedule = scheduleService.findById(id);
        model.addAttribute("schedule", ScheduleMapper.map(schedule));
        return "/manager/edit.html";
    }    
    
    @GetMapping("/save")
    public String save(ScheduleDto schedule) {
        scheduleService.save(schedule);
        return "redirect:show?employee_id=" + schedule.getEmployeeId() + "&year=" + schedule.getYear() + "&month=" + schedule.getMonth();
    }    
}
