package mk.com.kitchenandbarapp.web;

import mk.com.kitchenandbarapp.models.OrderItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/bar")
public class BarController {

    private final RestTemplate restTemplate;

    public BarController( RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String bar(Model model) {

        String apiUrl = "http://localhost:8080/api/barOrders";
        OrderItem[] barOrders = restTemplate.getForObject(apiUrl, OrderItem[].class);
        model.addAttribute("barOrders", barOrders);

        return "bar.html";
    }
}
