package mk.com.orderingsystem.service.implementation;


import mk.com.orderingsystem.models.Item;
import mk.com.orderingsystem.models.Order;
import mk.com.orderingsystem.models.OrderItem;
import mk.com.orderingsystem.models.exceptions.ItemNotFound;
import mk.com.orderingsystem.models.exceptions.OrderNotFound;
import mk.com.orderingsystem.repository.ItemRepository;
import mk.com.orderingsystem.repository.OrderItemRepository;
import mk.com.orderingsystem.repository.OrderRepository;
import mk.com.orderingsystem.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository repository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public OrderItemServiceImpl(OrderItemRepository repository, OrderRepository orderRepository, ItemRepository itemRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public OrderItem save(Long orderId, Long itemId, int quantity) {
        Order order = this.orderRepository.findById(orderId).orElseThrow(OrderNotFound::new);
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFound::new);
        OrderItem orderItem = new OrderItem(order,item,quantity);
        return this.repository.save(orderItem);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return this.repository.findAll();
    }

    @Override
    public void Delete(Long orderId, Long itemId) {
        OrderItem orderItem = this.repository.findById(orderId).orElseThrow(OrderNotFound::new);
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFound::new);
        if(orderItem.getItem().getId().equals(item.getId())){
            orderItem.setQuantity(orderItem.getQuantity() - 1);
            if (orderItem.getQuantity() == 0){
                this.repository.delete(orderItem);
            }
            else this.repository.save(orderItem);
        }
    }
}
