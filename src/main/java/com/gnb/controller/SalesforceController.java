package com.gnb.controller;

import com.gnb.dto.salesforce.SalesforceAuthDTO;
import com.gnb.dto.salesforce.SalesforceRespAuthDTO;
import com.gnb.util.HeaderInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping(path = "api/salesforce/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class SalesforceController {

  @Value("${salesforceLogin.url}")
  private String salesforceUrl;

  @Value("${salesforceCreate.url}")
  private String createUrl;

  private HeaderInterpreter headerInterpreter;

  private RestTemplate restTemplate;

  @Autowired
  public void setHeaderInterpreter(HeaderInterpreter headerInterpreter) {
    this.headerInterpreter = headerInterpreter;
  }

  @Autowired
  public void setRestTemplate(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @PostMapping()
  public SalesforceRespAuthDTO authenticateSalesforce(@RequestBody SalesforceAuthDTO data) {
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(salesforceUrl)
            .queryParam("grant_type", data.getGrant_type())
            .queryParam("client_id", data.getClient_id())
            .queryParam("client_secret", data.getClient_secret())
            .queryParam("username", data.getUsername())
            .queryParam("password", data.getPassword());
    ResponseEntity<SalesforceRespAuthDTO> rta = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, null, SalesforceRespAuthDTO.class);
    return rta.getBody();
  }

  @PostMapping(path = "create")
  public void createContact(@RequestHeader Map<String, String> requestHeaders) {

  }

}
