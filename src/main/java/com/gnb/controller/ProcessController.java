package com.gnb.controller;

import com.gnb.dto.process.ProcessDTO;
import com.gnb.dto.process.ProcessInstantiationRespDTO;
import com.gnb.util.HeaderInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping(path = "api/bonita/process/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ProcessController {

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

  @GetMapping(path = "queryProcess")
  public ProcessDTO[] queryProcess(@RequestHeader Map<String, String> requestHeaders, @RequestParam("processName") String processName) {
    HttpEntity<String> formEntity = headerInterpreter.parsingHeader2(requestHeaders);
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(bonitaUrl + "API/bpm/process")
            .queryParam("s", processName);
    ResponseEntity<ProcessDTO[]> rta = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, formEntity, ProcessDTO[].class);
    return rta.getBody();
  }

  @PostMapping(path = "processInstantiation/{processId}")
  public ProcessInstantiationRespDTO processIntantiation(@RequestHeader Map<String, String> requestHeaders, @PathVariable("processId") String processId) {
    HttpEntity<String> formEntity = headerInterpreter.parsingHeader2(requestHeaders);
    ResponseEntity<ProcessInstantiationRespDTO> rta = restTemplate.exchange(bonitaUrl + "/API/bpm/process/" + processId + "/instantiation", HttpMethod.POST, formEntity, ProcessInstantiationRespDTO.class);
    return rta.getBody();
  }
}
