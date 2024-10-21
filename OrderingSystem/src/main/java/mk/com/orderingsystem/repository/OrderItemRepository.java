package mk.com.orderingsystem.repository;

import mk.com.orderingsystem.models.Order;
import mk.com.orderingsystem.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrder(Order orderId);


}
