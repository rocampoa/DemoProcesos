package com.gnb.service;

import com.gnb.dto.DataCreditoDTO;
import com.gnb.repository.DataCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataCreditoService {

  private DataCreditoRepository dcr;

  @Autowired
  public void setDcr(DataCreditoRepository dcr) {
    this.dcr = dcr;
  }

  public DataCreditoDTO queryScore(String idNumber) {
    return dcr.findByIdNumber(idNumber);
  }

}
