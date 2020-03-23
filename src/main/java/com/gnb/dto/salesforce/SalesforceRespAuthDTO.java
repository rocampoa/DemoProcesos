package com.gnb.dto.salesforce;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesforceRespAuthDTO {
  private String access_token;
  private String instance_url;
  private String token_type;
  private String Bearer;
  private String issued_at;
  private String signature;
}
