package MapperTool.Controllers;

import MapperTool.POJOs.MazeMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping
    public String getIndex(Model model){
        return "home";
    }

    @PostMapping
    public String postIndex(@ModelAttribute("maze")String inputString, Model model) {
        model.addAttribute("completedMaze", MazeMapper.solve(inputString));
        return"display";
    }

    @GetMapping("/display")
    public String getDisplay(Model model){
        return"display";
    }
    @PostMapping("/display")
    public String postDisplay(Model model){
        return "home";
    }
}
