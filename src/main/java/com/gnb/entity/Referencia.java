package com.gnb.entity;

import com.gnb.dto.ReferenciaDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@SqlResultSetMapping(name = "referenciaMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ReferenciaDTO.class,
                        columns = {
                                @ColumnResult(name = "referenceId", type = Long.class),
                                @ColumnResult(name = "requestId", type = Long.class),
                                @ColumnResult(name = "names", type = String.class),
                                @ColumnResult(name = "relationship", type = String.class),
                                @ColumnResult(name = "address", type = String.class),
                                @ColumnResult(name = "phone", type = String.class),
                                @ColumnResult(name = "city", type = String.class),
                                @ColumnResult(name = "state", type = String.class),
                                @ColumnResult(name = "verified", type = Integer.class)
                        }
                )
        }
)

@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "referencia.queryByRequest",
                query = "Select id_referencia as referenceId, id_solicitud as requestId, nombres as names, parentesco as relationship," +
                        "       direccion as address, telefono as phone, ciudad as city, departamento as state, verificada as verified" +
                        "  From referencia" +
                        "  Where id_solicitud = :requestId",
                resultSetMapping = "referenciaMapping"
        ),
        @NamedNativeQuery(name = "referencia.queryByReference",
                query = "Select id_referencia as referenceId, id_solicitud as requestId, nombres as names, parentesco as relationship," +
                        "       direccion as address, telefono as phone, ciudad as city, departamento as state, verificada as verified" +
                        "  From referencia" +
                        "  Where id_referencia = :referenceId",
                resultSetMapping = "referenciaMapping"
        )
})

@Data
@NoArgsConstructor
@Entity
@Table(name = "referencia")
public class Referencia {

  @GenericGenerator(
          name = "referenceGenerator",
          strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
          parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "referencia_id_referencia_seq"),
                  @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                  @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")}
  )
  @Id
  @Column(name = "id_referencia", columnDefinition = "serial")
  @GeneratedValue(generator = "referenceGenerator")
  private Long referenceId;

  @Column(name = "id_solicitud")
  private Long requestId;

  @Column(name = "nombres")
  private String names;

  @Column(name = "parentesco")
  private String relationship;

  @Column(name = "direccion")
  private String address;

  @Column(name = "telefono")
  private String phone;

  @Column(name = "ciudad")
  private String city;

  @Column(name = "departamento")
  private String state;

  @Column(name = "verificada")
  private Integer verified;
}
