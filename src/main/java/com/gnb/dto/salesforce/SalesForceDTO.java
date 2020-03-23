package com.gnb.dto.salesforce;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesForceDTO {
  private String salutation;
  private String firstName;
  private String lastName;
  private String accountId;
  private String birthdate;
  private String leadSource;
  private Integer idDocument;
  private String documentType;
  private String documentIssueDate;
  private String documentIssuePlace;
  private Integer numberChildren;
  private String sex;
  private String maritalStatus;
  private String nationality;
  private String academicDegrees;
  private String twitterUser;
  private Integer numberDependentPeople;
  private String phone;
  private String mobilePhone;
  private String email;
  private String mailingStreet;
  private String mailingCity;
  private String mailingState;
  private String mailingPostalCode;
  private String mailingCountry;
  private String description;
}
