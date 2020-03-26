package com.gnb.entity;

import com.gnb.dto.CreditRequestDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@SqlResultSetMapping(name = "requestMapping",
        classes = {
                @ConstructorResult(
                        targetClass = CreditRequestDTO.class,
                        columns = {
                                @ColumnResult(name = "requestId", type = Long.class),
                                @ColumnResult(name = "refPId", type = String.class),
                                @ColumnResult(name = "refCId", type = String.class),
                                @ColumnResult(name = "contactId", type = String.class),
                                @ColumnResult(name = "status", type = Long.class),
                                @ColumnResult(name = "salary", type = Long.class),
                                @ColumnResult(name = "amount", type = Long.class),
                                @ColumnResult(name = "rent", type = Long.class),
                                @ColumnResult(name = "commissions", type = Long.class),
                                @ColumnResult(name = "income", type = Long.class),
                                @ColumnResult(name = "erent", type = Long.class),
                                @ColumnResult(name = "cards", type = Long.class),
                                @ColumnResult(name = "loans", type = Long.class),
                                @ColumnResult(name = "expenses", type = Long.class),
                        }
                )
        }
)

@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "request.queryByRequest",
                query = "Select requestid as requestId, refpid as refPId, refcid as refCId, contactid as contactId," +
                        "       status, salary, amount, rent, commissions, income, erent, cards, loans, expenses" +
                        "  From solicitud" +
                        "  Where requestid = :requestId",
                resultSetMapping = "requestMapping"
        ),
        @NamedNativeQuery(name = "request.queryByContact",
                query = "Select requestid as requestId, refpid as refPId, refcid as refCId, contactid as contactId," +
                        "       status, salary, amount, rent, commissions, income, erent, cards, loans, expenses" +
                        "  From solicitud" +
                        "  Where contactId = :contactId",
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
          parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "solicitud_requestId_seq"),
                  @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                  @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")}
  )
  @Id
  @Column(name = "requestid", columnDefinition = "serial")
  @GeneratedValue(generator = "requestGenerator")
  private Long requestId;

  @Column(name = "refpid")
  private String refPId;
  @Column(name = "refcid")
  private String refCId;
  @Column(name = "contactid")
  private String contactId;
  @Column(name = "status")
  private Long status;
  @Column(name = "salary")
  private Long salary;
  @Column(name = "amount")
  private Long amount;
  @Column(name = "rent")
  private Long rent;
  @Column(name = "commissions")
  private Long commissions;
  @Column(name = "income")
  private Long income;
  @Column(name = "erent")
  private Long erent;
  @Column(name = "cards")
  private Long cards;
  @Column(name = "loans")
  private Long loans;
  @Column(name = "expenses")
  private Long expenses;

}
