package com.gnb.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PollResponseDTO {
  private String rta1;
  private String rta2;
  private String rta3;
}
