package mk.com.orderingsystem.web;

import mk.com.orderingsystem.models.Item;
import mk.com.orderingsystem.models.Order;
import mk.com.orderingsystem.models.OrderItem;
import mk.com.orderingsystem.service.EmployeeService;
import mk.com.orderingsystem.service.ItemService;
import mk.com.orderingsystem.service.OrderItemService;
import mk.com.orderingsystem.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orderItem")
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final OrderService orderService;
    private final ItemService itemService;
    private final EmployeeService employeeService;

    public OrderItemController(OrderItemService orderItemService, OrderService orderService, ItemService itemService, EmployeeService employeeService) {
        this.orderItemService = orderItemService;
        this.orderService = orderService;
        this.itemService = itemService;
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public String orderItem(@PathVariable Long id, Model model) {
        List<Item> items = this.itemService.findAll();
        model.addAttribute("items", items);
        model.addAttribute("orderId", id);
        return "orderItem.html";
    }

    @PostMapping("/{id}")
    public String addOrderItem(@PathVariable Long id, @RequestParam Long itemId, int quantity) {
        Order order = this.orderService.getOrderById(id);
        List<OrderItem> orderItemsinOrder = order.getOrderItems();
        String currentUsername = employeeService.getCurrentUsername();
        boolean itemExists = false;
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getItem().getId().equals(itemId)) {
                orderItemsinOrder.remove(orderItem);
                int q = orderItem.getQuantity();
                q = q + quantity;
                this.orderItemService.save(id,orderItem.getItem().getId(),q);
                itemExists = true;
                break;
            }

        }
        if (!itemExists) {
            OrderItem newOrderItem = orderItemService.save(id, itemId, quantity);
            newOrderItem.setOrder(order);
        }
        return "redirect:/home";
    }


    @PostMapping("/delete/{id}")
    public String deleteOrderItem(@PathVariable Long id, @RequestParam Long itemId) {
        this.orderItemService.Delete(id,itemId);
        return "redirect:/home";
    }

}
