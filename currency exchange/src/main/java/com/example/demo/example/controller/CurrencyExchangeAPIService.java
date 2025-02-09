package com.example.demo.example.controller;

import com.example.demo.example.model.CurrencyExchangeRequestModel;
import com.example.demo.example.model.CurrencyExchangeResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping("/currencyExchange")
public class CurrencyExchangeAPIService {
    private static final String API_URL = "https://openexchangerates.org/api/latest.json?app_id=64f128ce2c71431b9d61678da6afeae7&base=USD"; //due to no subscription can only access usd

    @GetMapping("{base}")
    public ResponseEntity<String> getCurrencyExchangeRate(String base)

    {
        RestTemplate restTemplate = new RestTemplate();

        // Construct headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Create request entity
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the request
        String requestUrl = API_URL ;
        ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
    @PostMapping("/convert")

    public ResponseEntity< CurrencyExchangeResponseModel > convertCurrency(@RequestBody CurrencyExchangeRequestModel currencyExchangeRequestModel) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        // Construct headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Create request entity
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the request
        String requestUrl = API_URL ;
        ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, String.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Error: Unable to fetch exchange rates. API might be down.");
        }


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        // Extract exchange rates
        JsonNode ratesNode = jsonNode.get("rates");
        if (ratesNode == null) {
            throw new RuntimeException("Error: 'rates' field missing in API response.");
        }

        // ✅ Validate currency codes
        if (!ratesNode.has(currencyExchangeRequestModel.From)) {
            throw new IllegalArgumentException("Invalid currency code: " + currencyExchangeRequestModel.From);
        }
        if (!ratesNode.has(currencyExchangeRequestModel.To)) {
            throw new IllegalArgumentException("Invalid currency code: " + currencyExchangeRequestModel.To);
        }
        // Get rates for the currencies
        double fromRate = ratesNode.get(currencyExchangeRequestModel.From).asDouble();
        double toRate = ratesNode.get(currencyExchangeRequestModel.To).asDouble();

        // ✅ Convert currency: (Amount / FromRate) * ToRate


        // Convert currency: (Amount / FromRate) * ToRate
       // double convertedAmount = (amount / fromRate) * toRate;

          CurrencyExchangeResponseModel currencyExchangeResponseModel = new CurrencyExchangeResponseModel();
            currencyExchangeResponseModel.From = currencyExchangeRequestModel.From;
            currencyExchangeResponseModel.To = currencyExchangeRequestModel.To;
            currencyExchangeResponseModel.Amount = currencyExchangeRequestModel.Amount;
            // Convert amount
            currencyExchangeResponseModel.ConvertedAmount = (currencyExchangeRequestModel.Amount / fromRate) * toRate;

            return ResponseEntity.ok(currencyExchangeResponseModel);
        }

    }






