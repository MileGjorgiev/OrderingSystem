package mk.com.kitchenandbarapp.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class OrderItem {
    private Long id;

    private Order order;

    private Item item;

    private int quantity;


}
