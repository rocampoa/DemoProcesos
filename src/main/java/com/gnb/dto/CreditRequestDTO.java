package com.gnb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreditRequestDTO {

  private Long requestId;
  private String contactId;
  private Integer status;
  private BigDecimal salary;
  private BigDecimal bonus;
  private BigDecimal rent;
  private BigDecimal commissions;
  private BigDecimal income;
  private BigDecimal erent;
  private BigDecimal cards;
  private BigDecimal loans;
  private BigDecimal payrollDiscounts;
  private BigDecimal expenses;

  public CreditRequestDTO(Long requestId, String contactId, Integer status, BigDecimal salary, BigDecimal bonus, BigDecimal rent, BigDecimal commissions, BigDecimal income,
                          BigDecimal erent, BigDecimal cards, BigDecimal loans, BigDecimal payrollDiscounts, BigDecimal expenses) {
    this.requestId = requestId;
    this.contactId = contactId;
    this.status = status;
    this.salary = salary;
    this.bonus = bonus;
    this.rent = rent;
    this.commissions = commissions;
    this.income = income;
    this.erent = erent;
    this.cards = cards;
    this.loans = loans;
    this.payrollDiscounts = payrollDiscounts;
    this.expenses = expenses;
  }

}
