package com.gnb.dto.process;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessDTO {
  private String displayDescription;
  private String deploymentDate;
  private String displayName;
  private String name;
  private String description;
  private String deployedBy;
  private String id;
  private String activationState;
  private String version;
  private String configurationState;
  private String last_update_date;
  private String actorinitiatorid;
}
