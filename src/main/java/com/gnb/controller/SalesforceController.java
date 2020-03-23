package com.gnb.controller;

import com.gnb.dto.SalesforceAuthDTO;
import com.gnb.dto.SalesforceRespAuthDTO;
import com.gnb.util.HeaderInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = "api/salesforce/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class SalesforceController {
  @Value("${salesforceLogin.url}")
  private String salesforceUrl;

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
            .queryParam("grant_type", "password")
            .queryParam("client_id", "3MVG9Kip4IKAZQEUUGN7_eoZvZMqR7ZNaDZnPP2eh_yh_tskm.9bz82uwiWlWGsIFF5CymoZHtQTtvGG2CYod")
            .queryParam("client_secret", "5DAFD01B6FDACED58373E002F468F0255CBFEDA13A9D82C3C84167848D8C50BB")
            .queryParam("username", "bmartinezm@javeriana.edu.co")
            .queryParam("password", "DSkjdejude26q30xck12vhhWFytjWCoAIkRf");
    ResponseEntity<SalesforceRespAuthDTO> rta = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, null, SalesforceRespAuthDTO.class);
    return rta.getBody();
  }
}
