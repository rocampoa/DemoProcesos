package com.gnb.dto.salesforce;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactDTO {
  private String salutation;
  private String firstName;
  private String lastName;
  private Integer idDocument__c;
  private String documentType__c;
  private String documentIssuePlace__c;
  private Integer numberChildren__c;
  private String sex__c;
  private String maritalStatus__c;
  private String nationality__c;
  private String academicDegrees__c;
  private String twitterUser__c;
  private String numberDependentPeople__c;
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
