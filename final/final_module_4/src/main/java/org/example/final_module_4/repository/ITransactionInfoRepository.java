package org.example.final_module_4.repository;

import org.example.final_module_4.model.TransactionInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionInfoRepository extends JpaRepository<TransactionInfo,Long> {
    Page<TransactionInfo> findTransactionInfoByCategory_Id(Long categoryId, Pageable pageable);
    Page<TransactionInfo> findTransactionInfoByCustomer_NameContaining(String name,Pageable pageable);
    Page<TransactionInfo> findTransactionInfoByCustomer_NameContainingAndCategory_Id(String name, Long categoryId, Pageable pageable);
}
