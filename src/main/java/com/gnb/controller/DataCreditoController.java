package com.gnb.controller;

import com.gnb.dto.DataCreditoDTO;
import com.gnb.service.DataCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/datacredito/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class DataCreditoController {

  private DataCreditoService ds;

  @Autowired
  public void setDs(DataCreditoService ds) {
    this.ds = ds;
  }

  @GetMapping(path = "queryScoring/{idNumber}")
  public DataCreditoDTO queryScoring(@PathVariable(name = "idNumber") String idNumber) {
    return ds.queryScore(idNumber);
  }

}
