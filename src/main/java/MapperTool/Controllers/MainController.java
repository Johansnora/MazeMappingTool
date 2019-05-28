package MapperTool.Controllers;

import MapperTool.Utilities.MazeMapper;
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
        model.addAttribute("completed Maze", "");
        return "home";
    }

    @PostMapping
    public String postIndex(@ModelAttribute("maze")String inputString, Model model) {
        String solved = "This maze could not be solved";
        try {
            solved = MazeMapper.solve(inputString);
        } catch (NullPointerException e){
        }
        model.addAttribute("completedMaze", solved);
        return"home";
    }
}
