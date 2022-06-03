


package pl.mirekgab.presencelist.employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    

    @GetMapping("/list") 
    public String employeesList(Model model) {
        model.addAttribute("employees", employeeService.employeesList());
        return "employee/list";
    }
}
