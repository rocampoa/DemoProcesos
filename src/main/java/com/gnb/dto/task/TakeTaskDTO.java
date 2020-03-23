package com.gnb.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TakeTaskDTO {
  private String taskId;
  private String userId;
}
