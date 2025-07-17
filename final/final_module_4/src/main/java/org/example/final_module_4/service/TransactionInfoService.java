package org.example.final_module_4.service;

import org.example.final_module_4.model.TransactionInfo;
import org.example.final_module_4.repository.ITransactionInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionInfoService implements ITransactionInfoService{
    private final ITransactionInfoRepository transactionInfoRepository;

    public TransactionInfoService(ITransactionInfoRepository transactionInfoRepository) {
        this.transactionInfoRepository = transactionInfoRepository;
    }

    @Override
    public Page<TransactionInfo> findAll(Pageable pageable) {
        return transactionInfoRepository.findAll(pageable);
    }

    @Override
    public void save(TransactionInfo transactionInfo) {
        transactionInfoRepository.save(transactionInfo);
    }

    @Override
    public void remove(Long id) {
        transactionInfoRepository.deleteById(id);
    }

    @Override
    public TransactionInfo findById(Long id) {
        return transactionInfoRepository.findById(id).orElse(null);
    }

    @Override
    public Page<TransactionInfo> search(Long categoryId, Pageable pageable) {
            return transactionInfoRepository.findTransactionInfoByCategory_Id(categoryId,pageable);
    }
}
