package mk.com.orderingsystem.service;

import mk.com.orderingsystem.models.Item;
import mk.com.orderingsystem.models.enumeration.ItemType;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> findAll();
    Item findById(Long id);
    Item save(String name, int price, ItemType type);
    void deleteById(Long id);
    Item update(Long id, String name, int price,ItemType type);
}
