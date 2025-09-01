package skilltrack.skilltrack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import skilltrack.skilltrack.service.UserService;

@Controller
@RequestMapping("")

public class HomePageController {

    UserService userService;
    public HomePageController(UserService userService) {
        this.userService = userService;
    }



}
