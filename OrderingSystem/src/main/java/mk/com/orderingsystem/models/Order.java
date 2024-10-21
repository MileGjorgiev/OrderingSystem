package mk.com.orderingsystem.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "employee_username", referencedColumnName = "username")
    Employee employee;

    private LocalDateTime orderDate;

    private String specialRequests;

    public Order(Employee employee) {
        this.orderItems = new ArrayList<>();
        this.specialRequests = "";
        this.employee = employee;
        this.orderDate = LocalDateTime.now();
    }

}
