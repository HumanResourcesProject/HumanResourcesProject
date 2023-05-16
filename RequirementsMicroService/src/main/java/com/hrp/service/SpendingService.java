package com.hrp.service;

import com.hrp.mapper.IManuelRequirementsMapper;
import com.hrp.rabbitmq.model.ModelEmployeeExpense;
import com.hrp.repository.ISpendingRepository;
import com.hrp.repository.entity.Spending;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

@Service
public class SpendingService extends ServiceManagerImpl<Spending, Long> {
    private final ISpendingRepository spendingRepository;
    private final IManuelRequirementsMapper manuelRequirementsMapper;

    public SpendingService(ISpendingRepository spendingRepository,IManuelRequirementsMapper manuelRequirementsMapper) {
        super(spendingRepository);
        this.spendingRepository = spendingRepository;
        this.manuelRequirementsMapper = manuelRequirementsMapper;
    }

    public void createExpense(ModelEmployeeExpense modelEmployeeExpense) {
        System.out.println("spending kayıt ici");
        save(manuelRequirementsMapper.toSpending(modelEmployeeExpense));
        System.out.println("spending kayıt ici ve kayit sonrasi");
    }
}
