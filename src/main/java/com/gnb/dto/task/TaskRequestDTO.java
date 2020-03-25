package com.gnb.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Pojo que implementa el contrato que espera la actividad de solicitud de crédito.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class TaskRequestDTO {
  private String clientId;
  private String refCId;
  private String refPId;
}
