package mk.com.orderingsystem.web;

import jakarta.servlet.http.HttpServletRequest;
import mk.com.orderingsystem.models.Employee;
import mk.com.orderingsystem.models.exceptions.InvalidArgumentsException;
import mk.com.orderingsystem.models.exceptions.InvalidUserCredentialsException;
import mk.com.orderingsystem.service.AuthService;
import mk.com.orderingsystem.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;
    private final EmployeeService employeeService;

    public LoginController(AuthService authService, EmployeeService employeeService) {
        this.authService = authService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        Employee user = null;
        try {
            user = authService.login(request.getParameter("username"), request.getParameter("password"));
        } catch (InvalidUserCredentialsException | InvalidArgumentsException exception) {
            model.addAttribute("bodyContent", "login");
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "master-template";
        }

        request.getSession().setAttribute("user", user);
        return "redirect:/items";
    }
}

