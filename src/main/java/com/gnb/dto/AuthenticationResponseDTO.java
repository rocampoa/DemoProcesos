package com.gnb.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class AuthenticationResponseDTO {
  private String sessionId;
  private String token;
  private String message = "OK";

  public AuthenticationResponseDTO(List<String> data) {
    this.sessionId = data.stream().filter(x -> x.startsWith("JSESSIONID")).flatMap(y -> Arrays.stream(y.split(";"))).findFirst().orElse("").substring(11);
    this.token = data.stream().filter(x -> x.startsWith("X-Bonita-API-Token")).flatMap(y -> Arrays.stream(y.split(";"))).findFirst().orElse("").substring(19);
  }

  public AuthenticationResponseDTO(String message) {
    this.message = message;
  }
}
