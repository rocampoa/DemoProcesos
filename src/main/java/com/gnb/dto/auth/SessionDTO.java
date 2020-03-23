package com.gnb.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionDTO {
  private String copyright;
  private boolean is_guest_user;
  private String user_id;
  private String user_name;
  private String session_id;
  private String conf;
  private boolean is_technical_user;
  private String version;
}
