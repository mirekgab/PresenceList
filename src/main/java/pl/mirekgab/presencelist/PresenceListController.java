


package pl.mirekgab.presencelist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PresenceListController {

    @GetMapping("/index")
    public String start() {
        return "index.html";
    }
    
}
