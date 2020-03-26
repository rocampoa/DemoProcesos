package com.gnb.controller;

import com.gnb.dto.process.ProcessDTO;
import com.gnb.dto.process.ProcessInstantiationRespDTO;
import com.gnb.dto.process.ProcessVariableDTO;
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

/**
 * Clase que contiene la wrapper a los servicios de los procesos de Bonita.
 *
 * @author Byron Martinez, Javier Rivera, Rafael Ocampo
 * @version 1.0
 */
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

  /**
   * Consulta los procesos por el nombre del proceso
   *
   * @param requestHeaders Encabezados que deben contener el token y sessionid de Bonita
   * @param processName    Nombre del proceso que se desea consultar
   * @return La información del proceso
   */
  @GetMapping(path = "queryProcess")
  public ProcessDTO[] queryProcess(@RequestHeader Map<String, String> requestHeaders, @RequestParam("processName") String processName) {
    HttpEntity<String> formEntity = headerInterpreter.parsingHeader2(requestHeaders);
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(bonitaUrl + "API/bpm/process")
            .queryParam("s", processName);
    ResponseEntity<ProcessDTO[]> rta = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, formEntity, ProcessDTO[].class);
    return rta.getBody();
  }

  /**
   * Permite instanciar un proceso, es decir, iniciar la ejecución del mismo iniciando un case.
   *
   * @param requestHeaders Encabezados que deben contener el token y sessionid de Bonita
   * @param processId      Identificador del proceso que se desea instanciar
   * @return El número de la instacia caseId de proceso creada.
   */
  @PostMapping(path = "processInstantiation/{processId}")
  public ProcessInstantiationRespDTO processIntantiation(@RequestHeader Map<String, String> requestHeaders, @PathVariable("processId") String processId) {
    HttpEntity<String> formEntity = headerInterpreter.parsingHeader2(requestHeaders);
    ResponseEntity<ProcessInstantiationRespDTO> rta = restTemplate.exchange(bonitaUrl + "/API/bpm/process/" + processId + "/instantiation", HttpMethod.POST, formEntity,
            ProcessInstantiationRespDTO.class);
    return rta.getBody();
  }


  /**
   * Permite consultar el valor de una variable de instancia de proceso.
   *
   * @param requestHeaders Encabezados que deben contener el token y sessionid de Bonita
   * @param caseId         Identificador de la instancia de proceso para la cual se desea consultar la variable
   * @param varName        nombre de la variable que se desea consultar
   * @return Los datos de identificación de la variable de instancia de proceso y su valor.
   */
  @GetMapping(path = "queryVar")
  public ProcessVariableDTO queryVariable(@RequestHeader Map<String, String> requestHeaders, @RequestParam("caseId") String caseId, @RequestParam("varName") String varName) {
    HttpEntity<String> formEntity = headerInterpreter.parsingHeader2(requestHeaders);
    ResponseEntity<ProcessVariableDTO> rta = restTemplate.exchange(bonitaUrl + "API/bpm/caseVariable/" + caseId + "/" + varName, HttpMethod.GET, formEntity,
            ProcessVariableDTO.class);
    return rta.getBody();
  }

}
