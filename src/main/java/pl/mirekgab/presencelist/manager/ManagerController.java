package pl.mirekgab.presencelist.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mirekgab.presencelist.department.DepartmentService;
import pl.mirekgab.presencelist.employee.EmployeeService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @Autowired
    public ManagerController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping("/index")
    public String start(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "manager/index.html";
    }

    @GetMapping("/department")
    public String department(Model model, Long departmentId) {
        System.out.println(departmentId);
        model.addAttribute("department", departmentService.findById(departmentId));
        model.addAttribute("employees", employeeService.findByDepartmentId(departmentId));
        return "manager/department.html";
    }
}
