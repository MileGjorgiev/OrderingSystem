package mk.com.orderingsystem.service.implementation;
import jakarta.annotation.Nullable;
import mk.com.orderingsystem.models.Employee;
import mk.com.orderingsystem.models.Order;
import mk.com.orderingsystem.models.OrderItem;
import mk.com.orderingsystem.models.exceptions.OrderNotFound;
import mk.com.orderingsystem.repository.EmployeeRepository;
import mk.com.orderingsystem.repository.OrderItemRepository;
import mk.com.orderingsystem.repository.OrderRepository;
import mk.com.orderingsystem.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, EmployeeRepository employeeRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return this.orderRepository.findById(id).orElseThrow(OrderNotFound::new);
    }

    @Override
    public Order saveOrder(String employeeId) {
        Optional<Employee> employee = this.employeeRepository.findByUsername(employeeId);
        Order order = new Order(employee.orElse(null));
        return this.orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = this.orderRepository.findById(id).orElseThrow(OrderNotFound::new);
        this.orderRepository.delete(order);
    }

    @Override
    public Order updateOrder(Long id, @Nullable String special) {
        Order order = this.orderRepository.findById(id).orElseThrow(OrderNotFound::new);
        order.setSpecialRequests(special);
        return this.orderRepository.save(order);
    }
}
