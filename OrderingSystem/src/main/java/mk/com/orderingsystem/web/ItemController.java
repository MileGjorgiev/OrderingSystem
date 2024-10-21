package mk.com.orderingsystem.web;

import mk.com.orderingsystem.models.Item;
import mk.com.orderingsystem.models.enumeration.ItemType;
import mk.com.orderingsystem.service.ItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String getItems(Model model) {
        List<Item> items = this.itemService.findAll();
        model.addAttribute("items", items);
        return "items.html";
    }


    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addItem(Model model) {
        model.addAttribute("type", ItemType.values());
        return "addItem.html";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addItem(@RequestParam String name, @RequestParam int price, @RequestParam ItemType type) {
        this.itemService.save(name, price,type);
        return "redirect:/items";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String addItem(@PathVariable Long id, Model model) {
        Item item = this.itemService.findById(id);
        model.addAttribute("item", item);
        model.addAttribute("type", ItemType.values());
        return "editItem.html";
    }
    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editItem(@PathVariable Long id, @RequestParam String name, @RequestParam int price, @RequestParam ItemType type) {
        this.itemService.update(id, name, price,type);
        return "redirect:/items";
    }

    @PostMapping ("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        this.itemService.deleteById(id);
        return "redirect:/items";
    }







}
