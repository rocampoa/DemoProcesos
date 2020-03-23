package com.gnb.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HeaderInterpreter {

  public String parsingHeader(Map<String, String> data) {
    return "bonita.tenant=1; JSESSIONID=" + data.get("jsessionid") + "; X-Bonita-API-Token=" + data.get("x-bonita-api-token");
  }

  public HttpHeaders getHeaders(Map<String, String> requestHeaders) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Cookie", parsingHeader(requestHeaders));
    headers.add("X-Bonita-API-Token", requestHeaders.get("x-bonita-api-token"));
    return headers;
  }

  public HttpEntity<String> parsingHeader2(Map<String, String> data) {
    return new HttpEntity<>("", getHeaders(data));
  }
}
