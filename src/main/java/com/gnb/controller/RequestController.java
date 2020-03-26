package com.gnb.controller;

import com.gnb.dto.CreditRequestDTO;
import com.gnb.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/business/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class RequestController {

  private RequestService rs;

  @Autowired
  public void setRs(RequestService rs) {
    this.rs = rs;
  }

  @GetMapping(path = "{requestId}")
  public CreditRequestDTO queryRequest(@PathVariable(name = "requestId") Long requestId) {
    return this.rs.queryRequest(requestId);
  }

  @PostMapping()
  public CreditRequestDTO createRequest(@RequestBody CreditRequestDTO data) {
    return this.rs.createRequest(data);
  }

}
