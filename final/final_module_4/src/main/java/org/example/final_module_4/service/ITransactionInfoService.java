package org.example.final_module_4.service;

import org.example.final_module_4.model.TransactionInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITransactionInfoService {
    Page<TransactionInfo> findAll(Pageable pageable);
    void save(TransactionInfo transactionInfo);
    void remove(Long id);
    TransactionInfo findById(Long id);
    Page<TransactionInfo> search(Long categoryId,Pageable pageable);
}
