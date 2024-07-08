package mk.com.kitchenandbarapp.web;

import mk.com.kitchenandbarapp.models.OrderItem;
import mk.com.kitchenandbarapp.service.ExternalApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/kitchen")
public class KitchenController {

    private final ExternalApiService externalApiService;
    private final RestTemplate restTemplate; // Inject RestTemplate

    public KitchenController(ExternalApiService externalApiService, RestTemplate restTemplate) {
        this.externalApiService = externalApiService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String bar(Model model) {
        // Fetch data from external API endpoint
        String jsonData = this.externalApiService.getBarData();
        model.addAttribute("jsonData", jsonData);

        // Alternatively, fetch data directly from the API
        String apiUrl = "http://localhost:8080/api/kitchenOrders"; // Replace with actual API URL
        OrderItem[] kitchenOrders = restTemplate.getForObject(apiUrl, OrderItem[].class);
        model.addAttribute("kitchenOrders", kitchenOrders);

        return "kitchen.html";
    }
}