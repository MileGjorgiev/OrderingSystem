package mk.com.orderingsystem.web;

import mk.com.orderingsystem.models.Employee;
import mk.com.orderingsystem.models.enumeration.Role;
import mk.com.orderingsystem.service.EmployeeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {


    private final EmployeeService employeeService;
    public UsersController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping

    public String users(Model model) {
        List<Employee> employees = employeeService.getEmployeesByRole(Role.ROLE_USER);
        model.addAttribute("employees", employees);
        return "users.html";
    }

    @PostMapping("/approve/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public String approve(@PathVariable String username) {
        employeeService.approveRegistration(username);
        return "redirect:/users";
    }

    @PostMapping("/delete/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable String username) {
        employeeService.deleteEmployee(username);
        return "redirect:/users";
    }


}
