package com.gnb.dto.process;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessVariableDTO {
  private String description;
  private String name;
  private String value;
  private String case_id;
  private String type;
}
