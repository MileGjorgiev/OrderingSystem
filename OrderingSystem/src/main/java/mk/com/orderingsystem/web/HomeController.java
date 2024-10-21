package mk.com.orderingsystem.web;

import mk.com.orderingsystem.models.Employee;
import mk.com.orderingsystem.models.Order;
import mk.com.orderingsystem.service.EmployeeService;
import mk.com.orderingsystem.service.OrderItemService;
import mk.com.orderingsystem.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = {"/home"})
public class HomeController {

    private final OrderService orderService;
    private final EmployeeService employeeService;

    public HomeController(OrderService orderService, EmployeeService employeeService) {
        this.orderService = orderService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String home(Model model) {
        List<Order> orderList = null;
        String currentUsername = employeeService.getCurrentUsername();
        if (currentUsername != null) {
            orderList = this.orderService.getAllOrders().stream()
                    .filter(order -> order.getEmployee().getUsername().equals(currentUsername))
                    .collect(Collectors.toList());
        }
        model.addAttribute("orders",orderList);
        model.addAttribute("currentUsername", currentUsername);

        return "home.html";
    }

    @PostMapping("/addOrder")
    public String addOrder(@RequestParam String employee) {

        this.orderService.saveOrder(employee);
        return "redirect:/home";
    }


}
