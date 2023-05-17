package com.hrp.service;

import com.hrp.mapper.IManuelRequirementsMapper;
import com.hrp.rabbitmq.model.ModelBaseRequirmentFindAll;
import com.hrp.rabbitmq.model.ModelEmployeeExpense;
import com.hrp.rabbitmq.model.ModelTurnAllExpenseRequest;
import com.hrp.rabbitmq.producer.DirectProducer;
import com.hrp.repository.ISpendingRepository;
import com.hrp.repository.entity.Spending;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpendingService extends ServiceManagerImpl<Spending, Long> {
    private final ISpendingRepository spendingRepository;
    private final IManuelRequirementsMapper manuelRequirementsMapper;
    private final DirectProducer directProducer;

    public SpendingService(ISpendingRepository spendingRepository, IManuelRequirementsMapper manuelRequirementsMapper, DirectProducer directProducer) {
        super(spendingRepository);
        this.spendingRepository = spendingRepository;
        this.manuelRequirementsMapper = manuelRequirementsMapper;
        this.directProducer = directProducer;
    }

    public void createExpense(ModelEmployeeExpense modelEmployeeExpense) {
        System.out.println("spending kayıt ici");
        save(manuelRequirementsMapper.toSpending(modelEmployeeExpense));
        System.out.println("spending kayıt ici ve kayit sonrasi");
    }

    public void findAllExpenseRequestByCompany(ModelBaseRequirmentFindAll modelBaseRequirmentFindAll) {
        Optional<List<Spending>> spendingList =spendingRepository.findOptionalByCompany(modelBaseRequirmentFindAll.getCompany());
        System.out.println("spendingList..: " + spendingList.get());
        List<ModelTurnAllExpenseRequest> turnAllExpenseRequest = new ArrayList<>();
        for (Spending spending: spendingList.get()){
            turnAllExpenseRequest.add(manuelRequirementsMapper.toModelTurnAllExpenseRequest(spending));
        }
        directProducer.turnAllExpenseEmployee(turnAllExpenseRequest);
    }
}
