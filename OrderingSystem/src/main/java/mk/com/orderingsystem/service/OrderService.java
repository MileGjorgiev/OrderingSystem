package mk.com.orderingsystem.service;


import jakarta.annotation.Nullable;
import mk.com.orderingsystem.models.Order;
import mk.com.orderingsystem.models.OrderItem;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order saveOrder(String employeeId);
    void deleteOrder(Long id);
    Order updateOrder(Long id, @Nullable String special);
}
