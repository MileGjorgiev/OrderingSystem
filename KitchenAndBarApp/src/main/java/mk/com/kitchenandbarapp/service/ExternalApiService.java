package mk.com.kitchenandbarapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getBarData() {
        String apiUrl = "http://localhost:8080/api/barOrders"; // Replace with the actual URL of the external API
        return restTemplate.getForObject(apiUrl, String.class);
    }
}
