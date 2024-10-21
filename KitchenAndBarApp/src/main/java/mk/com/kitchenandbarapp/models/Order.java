package mk.com.kitchenandbarapp.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class Order {


    private Long Id;

    private List<OrderItem> orderItems;

    Employee employee;

    private LocalDateTime orderDate;

    private String specialRequests;


}
