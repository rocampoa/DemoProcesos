package com.gnb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenciaDTO {
  private Long referenceId;
  private Long requestId;
  private String names;
  private String relationship;
  private String address;
  private String phone;
  private String city;
  private String state;
  private Integer verified;
}
