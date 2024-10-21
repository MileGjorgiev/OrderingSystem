package mk.com.orderingsystem.web.api;

import mk.com.orderingsystem.models.Order;
import mk.com.orderingsystem.models.OrderItem;
import mk.com.orderingsystem.models.enumeration.ItemType;
import mk.com.orderingsystem.service.OrderItemService;
import mk.com.orderingsystem.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SendOrders {

    private final OrderItemService orderService;
    public SendOrders(OrderItemService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/barOrders")
    public List<OrderItem> sendBarOrders() {
        List<OrderItem> orderItems = orderService.getAllOrderItems();
        List<OrderItem> barOrders = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            if (orderItem.getItem().getItemType().equals(ItemType.DRINK)){
                barOrders.add(orderItem);
            }
        }
        return barOrders;
    }

    @GetMapping("/kitchenOrders")
    public List<OrderItem> sendKitchenOrders() {
        List<OrderItem> orderItems = orderService.getAllOrderItems();
        List<OrderItem> kitchenOrders = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            if (orderItem.getItem().getItemType().equals(ItemType.FOOD)){
                kitchenOrders.add(orderItem);
            }
        }
        return kitchenOrders;
    }




}
