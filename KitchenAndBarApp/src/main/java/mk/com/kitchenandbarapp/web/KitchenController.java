package mk.com.kitchenandbarapp.web;

import mk.com.kitchenandbarapp.models.OrderItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/kitchen")
public class KitchenController {


    private final RestTemplate restTemplate;

    public KitchenController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String bar(Model model) {

        String apiUrl = "http://localhost:8080/api/kitchenOrders";
        OrderItem[] kitchenOrders = restTemplate.getForObject(apiUrl, OrderItem[].class);
        model.addAttribute("kitchenOrders", kitchenOrders);

        return "kitchen.html";
    }
}