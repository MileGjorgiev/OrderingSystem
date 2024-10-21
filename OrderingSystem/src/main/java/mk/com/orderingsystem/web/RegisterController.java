package mk.com.orderingsystem.web;

import mk.com.orderingsystem.models.enumeration.Role;
import mk.com.orderingsystem.models.exceptions.InvalidArgumentsException;
import mk.com.orderingsystem.models.exceptions.UsernameAlreadyExistsException;
import mk.com.orderingsystem.service.AuthService;
import mk.com.orderingsystem.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final EmployeeService userService;

    public RegisterController(AuthService authService, EmployeeService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam Role role
    ) {
        try{
            this.userService.register(username, password, role);
            return "redirect:/login";
        } catch (InvalidArgumentsException | UsernameAlreadyExistsException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
