package mk.com.kitchenandbarapp.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import mk.com.kitchenandbarapp.models.enumeration.ItemType;


@Data
@NoArgsConstructor
public class Item {

    private Long id;

    private String name;
    private int Price;

    private ItemType itemType;


}
