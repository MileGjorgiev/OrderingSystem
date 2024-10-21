package mk.com.orderingsystem.web;

import mk.com.orderingsystem.models.Order;
import mk.com.orderingsystem.service.EmployeeService;
import mk.com.orderingsystem.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final EmployeeService employeeService;
    public OrderController(OrderService orderService, EmployeeService employeeService) {
        this.orderService = orderService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String order(Model model) {
        List<Order> orders = this.orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order.html";
    }


    @GetMapping("/updateOrder/{id}")
    public String updateOrder(@PathVariable Long id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "updateOrder.html";
    }



    @PostMapping("/updateOrder/{id}")
    public String updateOrder(@PathVariable Long id, @RequestParam (required = false) String special) {
        orderService.updateOrder(id, special);
        return "redirect:/home";
    }


    @PostMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/home";
    }

}
