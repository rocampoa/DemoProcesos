package com.gnb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditRequestDTO {

  private Long requestId;
  private String refPId;
  private String refCId;
  private String contactId;
  private Long status;
  private Long salary;
  private Long amount;
  private Long rent;
  private Long commissions;
  private Long income;
  private Long erent;
  private Long cards;
  private Long loans;
  private Long expenses;

}
