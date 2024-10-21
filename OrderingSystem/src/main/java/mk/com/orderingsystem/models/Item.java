package mk.com.orderingsystem.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.com.orderingsystem.models.enumeration.ItemType;

@Entity
@Data
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int Price;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;


    public Item(String name, int Price, ItemType itemType) {
        this.name = name;
        this.Price = Price;
        this.itemType = itemType;
    }


}
