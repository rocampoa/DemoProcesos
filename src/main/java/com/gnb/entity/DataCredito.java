package com.gnb.entity;

import com.gnb.dto.DataCreditoDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SqlResultSetMapping(name = "dataCredMappping",
        classes = {
                @ConstructorResult(targetClass = DataCreditoDTO.class,
                        columns = {
                                @ColumnResult(name = "identificationNumber", type = String.class),
                                @ColumnResult(name = "score", type = Integer.class)
                        })
        })

@NamedNativeQuery(name = "datacredito.Consulta",
        query = "Select client_id As identificationNumber, score" +
                "  From datacredito" +
                "  Where client_id = :idNumber",
        resultSetMapping = "dataCredMappping")

@Data
@NoArgsConstructor
@Entity
@Table(name = "datacredito")
public class DataCredito {
  @Id
  @Column(name = "client_id")
  private String identificationNumber;

  private Integer score;
}
