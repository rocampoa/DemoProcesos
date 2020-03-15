package com.gnb.repository;

import com.gnb.dto.ReferenciaDTO;
import com.gnb.entity.Referencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ReferenciaRepository extends JpaRepository<Referencia, Long> {

  @Transactional
  @Modifying
  @Query(value = "Insert Into referencia" +
          "  (id_solicitud, nombres, parentesco, direccion, telefono, ciudad, departamento, verificada)" +
          " Values" +
          "  (:requestId, :names, :relationship, :address, :phone, :city, :state, :verified)", nativeQuery = true)
  void insertReference(@Param("requestId") Long requestId, @Param("names") String names, @Param("relationship") String relationship, @Param("address") String address,
                       @Param("phone") String phone, @Param("city") String city, @Param("state") String state, @Param("verified") Integer verified);

  @Transactional
  @Modifying
  @Query(value = "Update referencia" +
          "  Set nombres = :names, " +
          "      parentesco = :relationship," +
          "      direccion = :address," +
          "      telefono = :phone," +
          "      ciudad = :city," +
          "      departamento = :state," +
          "      verificada = :verified" +
          "  Where id_referencia = :referenceId", nativeQuery = true)
  void updateReference(@Param("referenceId") Long referenceId, @Param("names") String names, @Param("relationship") String relationship, @Param("address") String address,
                       @Param("phone") String phone, @Param("city") String city, @Param("state") String state, @Param("verified") Integer verified);

  @Transactional
  @Modifying
  @Query(value = "Update referencia" +
          "  Set verificada = :verified" +
          "  Where id_referencia = :referenceId", nativeQuery = true)
  void changeStatus(@Param("referenceId") Long referenceId, @Param("verified") Integer verified);

  @Query(name = "referencia.queryByRequest", nativeQuery = true)
  List<ReferenciaDTO> getReferencesByRequest(@Param("requestId") Long requestId);

  @Query(name = "referencia.queryByReference", nativeQuery = true)
  ReferenciaDTO getReferenceById(@Param("referenceId") Long referenceId);

}
