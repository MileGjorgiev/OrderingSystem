package mk.com.orderingsystem.service;

import mk.com.orderingsystem.models.Employee;
import mk.com.orderingsystem.models.Item;
import mk.com.orderingsystem.models.OrderItem;
import mk.com.orderingsystem.models.enumeration.ItemType;

import java.util.List;

public interface OrderItemService {
    OrderItem save(Long orderId, Long itemId, int quantity);
    void Delete(Long orderId, Long itemId);
    List<OrderItem> getAllOrderItems();
}
