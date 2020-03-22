package com.gnb.controller;

import com.gnb.dto.AuthenticationDTO;
import com.gnb.dto.AuthenticationResponseDTO;
import com.gnb.dto.DataCreditoDTO;
import com.gnb.service.DataCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "api/datacredito/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class DataCreditoController {

  private DataCreditoService ds;

  @Value("${bonitaLogin.url}")
  private String bonitaUrl;

  @Autowired
  public void setDs(DataCreditoService ds) {
    this.ds = ds;
  }

  @GetMapping(path = "queryScoring/{idNumber}")
  public DataCreditoDTO queryScoring(@PathVariable(name = "idNumber") String idNumber) {
    return ds.queryScore(idNumber);
  }

  @PostMapping(path = "loginBonita")
  public AuthenticationResponseDTO loginBonita(@RequestBody AuthenticationDTO data) {
    AuthenticationResponseDTO result = null;
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
    String parameter = new StringBuilder("username=")
            .append(data.getUserName())
            .append("&password=")
            .append(data.getPassword())
            .append("&redirect=false&redirectURL=").toString();

    HttpEntity<String> formEntity = new HttpEntity(parameter, headers);
    ResponseEntity<String> rta = null;
    try {
      rta = restTemplate.postForEntity(bonitaUrl, formEntity, String.class);
      result = new AuthenticationResponseDTO(rta.getHeaders().get("Set-Cookie"));
    } catch (HttpClientErrorException e) {
      if (e.getRawStatusCode() == 401) {
        result = new AuthenticationResponseDTO("Usuario o clave incorrectos");
      } else {
        result = new AuthenticationResponseDTO("Error autenticandose en bonita " + e.fillInStackTrace() + " " + e.getMessage());
      }
    }
    return result;
  }

}
