package com.gnb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesforceAuthDTO {
  private String grant_type;
  private String client_id;
  private String client_secret;
  private String username;
  private String password;
}
