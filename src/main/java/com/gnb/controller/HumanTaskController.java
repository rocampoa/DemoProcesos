package com.gnb.controller;

import com.gnb.dto.task.*;
import com.gnb.util.HeaderInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Clase que se enccarga de se un wrapper para los servicios gestión de tareas con Bonita
 *
 * @Version 1.0
 */
@RestController
@RequestMapping(path = "api/bonita/humanTask")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class HumanTaskController {

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

  /**
   * Permite consultar las tareas pendientes de un usuario, o de un usuario y una instancia de proceso específica.
   *
   * @param requestHeaders
   * @param userId
   * @param caseId
   * @return
   */
  @GetMapping()
  public HumanTaskDTO[] taskByUser(@RequestHeader Map<String, String> requestHeaders, @RequestParam("userId") String userId, @RequestParam("caseId") Optional<String> caseId) {
    HumanTaskDTO[] result;
    HttpEntity<String> formEntity = headerInterpreter.parsingHeader2(requestHeaders);
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(bonitaUrl + "API/bpm/humanTask")
            .queryParam("c", "50")
            .queryParam("f", "state=ready")
            .queryParam("f", "user=" + userId)
            .queryParam("p", "0");
    ResponseEntity<HumanTaskDTO[]> rta = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, formEntity, HumanTaskDTO[].class);
    result = rta.getBody();
    if (caseId.isPresent()) {
      List<HumanTaskDTO> aux = Arrays.stream(result).filter(x -> x.getCaseId().equals(caseId.get())).collect(Collectors.toList());
      result = new HumanTaskDTO[aux.size()];
      result = aux.toArray(result);
    }
    return result;
  }

  @PutMapping()
  public String takeTask(@RequestHeader Map<String, String> requestHeaders, @RequestBody TakeTaskDTO data) {
    TakeTaskSendDTO parameter = new TakeTaskSendDTO(data.getUserId());
    HttpEntity<TakeTaskSendDTO> formEntity = new HttpEntity<>(parameter, headerInterpreter.getHeaders(requestHeaders));
    ResponseEntity<String> rta = restTemplate.exchange(bonitaUrl + "API/bpm/humanTask/" + data.getTaskId(), HttpMethod.PUT, formEntity, String.class);
    return "{\"rta\":\"OK\"}";
  }

  @PostMapping(path = "endTaskRequest/{taskId}")
  public String sendCreditRequest(@RequestHeader Map<String, String> requestHeaders, @RequestBody TaskRequestDTO data, @PathVariable("taskId") String taskId) {
    HttpEntity<TaskRequestDTO> formEntity = new HttpEntity<>(data, headerInterpreter.getHeaders(requestHeaders));
    ResponseEntity<String> rta = restTemplate.exchange(bonitaUrl + "API/bpm/userTask/" + taskId + "/execution?assign=false", HttpMethod.POST, formEntity, String.class);
    return rta.getBody();
  }

  @PostMapping(path = "endTaskValidate/{taskId}")
  public String sendValidate(@RequestHeader Map<String, String> requestHeaders, @RequestBody TaskValidateDTO data, @PathVariable("taskId") String taskId) {
    HttpEntity<TaskValidateDTO> formEntity = new HttpEntity<>(data, headerInterpreter.getHeaders(requestHeaders));
    ResponseEntity<String> rta = restTemplate.exchange(bonitaUrl + "API/bpm/userTask/" + taskId + "/execution?assign=false", HttpMethod.POST, formEntity, String.class);
    return rta.getBody();
  }

}
