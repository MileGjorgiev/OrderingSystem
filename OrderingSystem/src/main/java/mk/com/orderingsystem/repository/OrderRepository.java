package mk.com.orderingsystem.repository;


import mk.com.orderingsystem.models.Employee;
import mk.com.orderingsystem.models.Order;
import mk.com.orderingsystem.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByEmployee(Employee employee);
    List<Order> findAllByOrderItems(OrderItem item);
}
