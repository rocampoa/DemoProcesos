package com.gnb.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HumanTaskDTO {
  private String displayDescription;
  private String executedBy;
  private String rootContainerId;
  private String assigned_date;
  private String displayName;
  private String executedBySubstitute;
  private String dueDate;
  private String description;
  private String type;
  private String priority;
  private String actorId;
  private String processId;
  private String caseId;
  private String name;
  private String reached_state_date;
  private String rootCaseId;
  private String id;
  private String state;
  private String parentCaseId;
  private String last_update_date;
  private String assigned_id;
}
