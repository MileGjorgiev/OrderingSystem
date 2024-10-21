package mk.com.orderingsystem.service.implementation;

import mk.com.orderingsystem.models.Item;
import mk.com.orderingsystem.models.enumeration.ItemType;
import mk.com.orderingsystem.models.exceptions.ItemNotFound;
import mk.com.orderingsystem.repository.ItemRepository;
import mk.com.orderingsystem.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }




    @Override
    public List<Item> findAll() {
        return this.itemRepository.findAll();
    }

    @Override
    public Item findById(Long id) {
        Item item = this.itemRepository.findById(id).orElseThrow(ItemNotFound::new);
        return item;
    }

    @Override
    public Item save(String name, int price, ItemType type) {
        Item item = new Item(name, price,type);
        return this.itemRepository.save(item);
    }

    @Override
    public void deleteById(Long id) {
        Item item = this.itemRepository.findById(id).orElseThrow(ItemNotFound::new);
        this.itemRepository.delete(item);
    }

    @Override
    public Item update(Long id, String name, int price,ItemType type) {
        Item item = this.itemRepository.findById(id).orElseThrow(ItemNotFound::new);
        item.setName(name);
        item.setPrice(price);
        item.setItemType(type);
        return this.itemRepository.save(item);
    }
}
