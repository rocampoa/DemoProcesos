package com.gnb.dto.salesforce;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateContactRespDTO {
  private String id;
  private Boolean success;
  private String[] errors;
}
