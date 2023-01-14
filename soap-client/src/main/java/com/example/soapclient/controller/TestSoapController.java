package com.example.soapclient.controller;

import com.example.soapclient.client.CountryClient;
import com.example.soapclient.client.gen.GetCountryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class TestSoapController {

    private final CountryClient countryClient;
    public TestSoapController(CountryClient countryClient) {
        this.countryClient = countryClient;
    }

    @GetMapping("/country")
    public GetCountryResponse testSoap(){
        return countryClient.getCountry("Spain");
    }
}
