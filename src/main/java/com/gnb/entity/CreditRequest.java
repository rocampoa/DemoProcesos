package com.gnb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "solicitud")
public class CreditRequest {
  @GenericGenerator(
          name = "requestGenerator",
          strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
          parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "Solicitud_IdSolicitud_seq"),
                        @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                        @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")}
  )
  @Id
  @Column(name = "id_solicitud", columnDefinition = "serial")
  @GeneratedValue(generator = "requestGenerator")
  private Long requestId;


}
