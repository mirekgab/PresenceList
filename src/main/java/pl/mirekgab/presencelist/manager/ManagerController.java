package pl.mirekgab.presencelist.manager;

import java.util.Arrays;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mirekgab.presencelist.MonthsName;
import pl.mirekgab.presencelist.department.Department;
import pl.mirekgab.presencelist.department.DepartmentService;
import pl.mirekgab.presencelist.employee.EmployeeService;
import pl.mirekgab.presencelist.schedule.ScheduleService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private static final Logger LOG = Logger.getLogger(ManagerController.class.getName());
    

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final ScheduleService scheduleService;

    @Autowired
    public ManagerController(DepartmentService departmentService, 
            EmployeeService employeeService, 
            ScheduleService scheduleService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/index")
    public String start(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("years", Arrays.asList(2020,2021,2022));
        model.addAttribute("months", MonthsName.months);
        return "manager/index.html";
    }

    @GetMapping("/department")
    public String department(Model model, Long departmentId, int year, int month) {
        LOG.info(String.valueOf(departmentId+" "+year+" "+month));
        Department department = departmentService.findById(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("month", MonthsName.months[month]);
        scheduleService.findByDepartmentAndYearAndMonth(department, year, month);
        model.addAttribute("employees", employeeService.findByDepartmentId(departmentId));
        return "manager/department.html";
    }
    
}
