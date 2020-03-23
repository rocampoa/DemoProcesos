package com.gnb.controller;

import com.gnb.dto.AuthenticationDTO;
import com.gnb.dto.AuthenticationResponseDTO;
import com.gnb.dto.ProcessDTO;
import com.gnb.dto.SessionDTO;
import com.gnb.util.HeaderInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/bonita/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class BonitaController {

  @Value("${bonitaLogin.url}")
  private String bonitaUrl;

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

  @PostMapping(path = "loginBonita")
  public AuthenticationResponseDTO loginBonita(@RequestBody AuthenticationDTO data) {
    AuthenticationResponseDTO result;
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
    String parameter = "username=" + data.getUserName() + "&password=" + data.getPassword() + "&redirect=false&redirectURL=";

    HttpEntity<String> formEntity = new HttpEntity<>(parameter, headers);
    try {
      ResponseEntity<String> rta = restTemplate.postForEntity(bonitaUrl + "loginservice", formEntity, String.class);
      System.out.println(rta.getHeaders().get("Set-Cookie"));
      result = new AuthenticationResponseDTO(Objects.requireNonNull(rta.getHeaders().get("Set-Cookie")));
    } catch (HttpClientErrorException e) {
      if (e.getRawStatusCode() == 401) {
        result = new AuthenticationResponseDTO("Usuario o clave incorrectos");
      } else {
        result = new AuthenticationResponseDTO("Error autenticandose en bonita " + e.fillInStackTrace() + " " + e.getMessage());
      }
    }
    return result;
  }

  @GetMapping(path = "session")
  public SessionDTO getSession(@RequestHeader Map<String, String> requestHeaders) {
    HttpEntity<String> formEntity = headerInterpreter.parsingHeader2(requestHeaders);
    ResponseEntity<SessionDTO> rta = restTemplate.exchange(bonitaUrl + "API/system/session/unusedid", HttpMethod.GET, formEntity, SessionDTO.class);
    return rta.getBody();
  }

  @GetMapping(path = "queryProcess")
  public ProcessDTO[] queryProcess(@RequestHeader Map<String, String> requestHeaders, @RequestParam("processName") String processName) {
    HttpEntity<String> formEntity = headerInterpreter.parsingHeader2(requestHeaders);
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(bonitaUrl + "API/bpm/process")
            .queryParam("s", processName);
    ResponseEntity<ProcessDTO[]> rta = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, formEntity, ProcessDTO[].class);
    return rta.getBody();
  }

  @PostMapping(path = "processInstantiation/{processId}")
  public String processIntantiation(@RequestHeader Map<String, String> requestHeaders, @PathVariable("processId") String processId) {
    HttpEntity<String> formEntity = headerInterpreter.parsingHeader2(requestHeaders);
    ResponseEntity<String> rta = restTemplate.exchange(bonitaUrl + "/API/bpm/process/" + processId + "/instantiation", HttpMethod.POST, formEntity, String.class);
    return rta.getBody();
  }

}
