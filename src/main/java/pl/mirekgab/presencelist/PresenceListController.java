


package pl.mirekgab.presencelist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PresenceListController {

    @GetMapping("/")
    public String start() {
        return "index";
    }
    
}
