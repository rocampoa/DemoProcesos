package com.gnb.service;

import com.gnb.dto.CreditRequestDTO;
import com.gnb.repository.CreditRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

  private CreditRequestRepository cr;

  @Autowired
  public void setCr(CreditRequestRepository cr) {
    this.cr = cr;
  }

  public CreditRequestDTO createRequest(CreditRequestDTO data) {
    cr.insertRequest(data.getRefPId(), data.getRefCId(), data.getContactId(), data.getStatus(), data.getSalary(), data.getAmount(), data.getRent(), data.getCommissions(),
            data.getIncome(), data.getErent(), data.getCards(), data.getLoans(), data.getExpenses());
    return cr.getRequestByContact(data.getContactId());
  }

  public CreditRequestDTO queryRequest(Long requestId) {
    return cr.getRequest(requestId);
  }


}
