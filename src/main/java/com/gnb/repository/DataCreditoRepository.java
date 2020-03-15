package com.gnb.repository;

import com.gnb.dto.DataCreditoDTO;
import com.gnb.entity.DataCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataCreditoRepository extends JpaRepository<DataCredito, String> {

  @Query(name = "datacredito.Consulta", nativeQuery = true)
  DataCreditoDTO findByIdNumber(@Param("idNumber") String idNumber);

}
