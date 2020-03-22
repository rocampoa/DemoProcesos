package com.gnb.repository;

import com.gnb.dto.CreditRequestDTO;
import com.gnb.entity.CreditRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
public interface CreditRequestRepository extends JpaRepository<CreditRequest, Long> {

  @Transactional
  @Modifying
  @Query(value = "Insert Into solicitud" +
          "  (id_contacto, estado, salario, bonificacion, arriendos, comisiones, otros_ingresos, e_arriendo, tarjetas, prestamos, dctos_nomina, otrosegresos)" +
          "Values" +
          "  (:contactId, :status, :salary, :bonus, :rent, :commissions, :income, :erent, :cards, :loans, :payrollDiscounts, :expenses)", nativeQuery = true)
  void insertRequest(@Param("contactId") String contactId, @Param("status") Integer status, @Param("salary") BigDecimal salary, @Param("bonus") BigDecimal bonus,
                     @Param("rent") BigDecimal rent, @Param("commissions") BigDecimal commissions, @Param("income") BigDecimal income, @Param("erent") BigDecimal erent,
                     @Param("cards") BigDecimal cards, @Param("loans") BigDecimal loans, @Param("payrollDiscounts") BigDecimal payrollDiscounts,
                     @Param("expenses") BigDecimal expenses);

  @Transactional
  @Modifying
  @Query(value = "Update solicitud" +
          "  Set estado = :status," +
          "      salario = :salary," +
          "      bonificacion = :bonus," +
          "      arriendos = :rent," +
          "      comisiones = :commissions," +
          "      otros_ingresos = :income," +
          "      e_arriendo = :erent," +
          "      tarjetas = :cards," +
          "      prestamos = :loans," +
          "      dctos_nomina = :payrollDiscounts," +
          "      otrosegresos = :expenses" +
          "  Where id_solicitud = :requestId", nativeQuery = true)
  void updateRequest(@Param("requestId") Long requestId, @Param("status") Integer status, @Param("salary") BigDecimal salary, @Param("bonus") BigDecimal bonus,
                     @Param("rent") BigDecimal rent, @Param("commissions") BigDecimal commissions, @Param("income") BigDecimal income, @Param("erent") BigDecimal erent,
                     @Param("cards") BigDecimal cards, @Param("loans") BigDecimal loans, @Param("payrollDiscounts") BigDecimal payrollDiscounts,
                     @Param("expenses") BigDecimal expenses);

  @Transactional
  @Modifying
  @Query(value = "Update solicitud" +
          "  Set estado = :status" +
          "  Where id_solicitud = :requestId", nativeQuery = true)
  void changeStatus(@Param("requestId") Long requestId, @Param("status") Integer status);

  @Query(name = "request.queryByRequest", nativeQuery = true)
  CreditRequestDTO getRequest(@Param("requestId") Long requestId);

  @Query(name = "request.queryByContact", nativeQuery = true)
  CreditRequestDTO getRequestByContact(@Param("contactId") String contactId);

}
