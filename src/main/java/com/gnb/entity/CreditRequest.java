package com.gnb.entity;

import com.gnb.dto.ReferenciaDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@SqlResultSetMapping(name = "requestMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ReferenciaDTO.class,
                        columns = {
                                @ColumnResult(name = "requestId", type = Long.class),
                                @ColumnResult(name = "contactId", type = String.class),
                                @ColumnResult(name = "status", type = Integer.class),
                                @ColumnResult(name = "salary", type = BigDecimal.class),
                                @ColumnResult(name = "bonus", type = BigDecimal.class),
                                @ColumnResult(name = "rent", type = BigDecimal.class),
                                @ColumnResult(name = "commissions", type = BigDecimal.class),
                                @ColumnResult(name = "erent", type = BigDecimal.class),
                                @ColumnResult(name = "cards", type = BigDecimal.class),
                                @ColumnResult(name = "loans", type = BigDecimal.class),
                                @ColumnResult(name = "payrollDiscounts", type = BigDecimal.class),
                                @ColumnResult(name = "expenses", type = BigDecimal.class),
                        }
                )
        }
)

@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "request.queryByRequest",
                query = "Select id_solicitud as requestId, id_contacto as contactId, estado as status, salario as salary, bonificacion as bonues," +
                        "       arriendos as rent, comisiones as commissions, otros_ingresos as income, e_arriendo as erent, tarjetas as cards," +
                        "       prestamos as loans, dctos_nomina as payrollDiscounts, otrosegresos as expenses" +
                        "  From solicitud" +
                        "  Where id_solicitud = :requestId",
                resultSetMapping = "requestMapping"
        ),
        @NamedNativeQuery(name = "request.queryByContact",
                query = "Select id_solicitud as requestId, id_contacto as contactId, estado as status, salario as salary, bonificacion as bonues," +
                        "       arriendos as rent, comisiones as commissions, otros_ingresos as income, e_arriendo as erent, tarjetas as cards," +
                        "       prestamos as loans, dctos_nomina as payrollDiscounts, otrosegresos as expenses" +
                        "  From solicitud" +
                        "  Where id_contacto = :contactId",
                resultSetMapping = "requestMapping"
        )
})

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
