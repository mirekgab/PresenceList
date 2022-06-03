


package pl.mirekgab.presencelist.hr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hr")
public class HrController {

    @GetMapping("/index")
    public String start() {
        return "hr/index";
    }
}
