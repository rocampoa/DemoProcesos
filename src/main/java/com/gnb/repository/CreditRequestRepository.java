package com.gnb.repository;

import com.gnb.dto.CreditRequestDTO;
import com.gnb.entity.CreditRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CreditRequestRepository extends JpaRepository<CreditRequest, Long> {

  @Transactional
  @Modifying
  @Query(value = "Insert Into Solicitud " +
          "  (refpid, refcid, contactid, status, salary, amount, rent, commissions, income, erent, cards, loans, expenses)" +
          " Values" +
          "  (:refPId, :refCId, :contactId, :status, :salary, :amount, :rent, :commissions, :income, :erent, :cards,:loans, :expenses)", nativeQuery = true)
  void insertRequest(@Param("refPId") String refPId, @Param("refCId") String refCId, @Param("contactId") String contactId, @Param("status") Long status,
                     @Param("salary") Long salary, @Param("amount") Long amount, @Param("rent") Long rent, @Param("commissions") Long commissions, @Param("income") Long income,
                     @Param("erent") Long erent, @Param("cards") Long cards, @Param("loans") Long loans, @Param("expenses") Long expenses);

  @Transactional
  @Modifying
  @Query(value = "Update Solicitud" +
          "  Set refPid = :refPId," +
          "      refCid = :refCId," +
          "      contactid = :contactId," +
          "      status = :status," +
          "      salary = :salary," +
          "      amount = :amount," +
          "      rent = :rent," +
          "      commissions = :commissions, " +
          "      income = :income, " +
          "      erent = :erent, " +
          "      cards = :cards, " +
          "      loans = :loans, " +
          "      expenses = :expenses" +
          "  Where requestId = :requestId", nativeQuery = true)
  void updateRequest(@Param("requestId") Long requestId, @Param("refPId") String refPId, @Param("refCId") String refCId, @Param("contactId") String contactId, @Param("status") Long status,
                     @Param("salary") Long salary, @Param("amount") Long amount, @Param("rent") Long rent, @Param("commissions") Long commissions, @Param("income") Long income,
                     @Param("erent") Long erent, @Param("cards") Long cards, @Param("loans") Long loans, @Param("expenses") Long expenses);

  @Transactional
  @Modifying
  @Query(value = "Update solicitud" +
          "  Set status = :status" +
          "  Where requestId = :requestId", nativeQuery = true)
  void changeStatus(@Param("requestId") Long requestId, @Param("status") Long status);

  @Query(name = "request.queryByRequest", nativeQuery = true)
  CreditRequestDTO getRequest(@Param("requestId") Long requestId);

  @Query(name = "request.queryByContact", nativeQuery = true)
  CreditRequestDTO getRequestByContact(@Param("contactId") String contactId);

}
