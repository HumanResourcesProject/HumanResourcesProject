package com.hrp.service;

import com.hrp.dto.request.BaseEmployeeRequestDto;
import com.hrp.dto.request.ExpenseRequestDto;
import com.hrp.dto.request.requirements.AdvancePaymentRequestDto;
import com.hrp.dto.request.requirements.LeaveRequestDto;
import com.hrp.dto.response.BaseEmployeeResponseDto;
import com.hrp.exception.EErrorType;
import com.hrp.exception.EmployeeException;
import com.hrp.mapper.IManuelEmployeeMapper;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.rabbitmq.producer.DirectProducer;
import com.hrp.repository.IEmployeeRepository;
import com.hrp.repository.entity.Employee;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService extends ServiceManagerImpl<Employee,String> {

    private final IEmployeeRepository employeeRepository;
    private final IManuelEmployeeMapper iManuelEmployeeMapper;
    private final JwtTokenManager jwtTokenManager;

    private final DirectProducer directProducer;

    public EmployeeService(IEmployeeRepository employeeRepository, IManuelEmployeeMapper iManuelEmployeeMapper, JwtTokenManager jwtTokenManager,DirectProducer directProducer) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
        this.iManuelEmployeeMapper = iManuelEmployeeMapper;
        this.jwtTokenManager = jwtTokenManager;
        this.directProducer= directProducer;
    }


    public void createEmployee(ModelRegisterEmployee model){
        Employee employee= iManuelEmployeeMapper.modelToEmployee(model);
        employee.setSalary(5000L);
        employee.setAvatar("https://gcavocats.ca/wp-content/uploads/2018/09/man-avatar-icon-flat-vector-19152370-1.jpg");
        save(employee);
    }


    public List<BaseEmployeeResponseDto> findAll(BaseEmployeeRequestDto dto) {
        Optional<Long> id = jwtTokenManager.validToken(dto.getToken());
        Optional<Employee> employee= employeeRepository.findOptionalByAuthId(id.get());
        if (dto.getRole().equals("ADMIN")){
            return findAll().stream()
                    .map(x-> iManuelEmployeeMapper.toBaseEmployeeDto(x))
                    .collect(Collectors.toList());
        }else {
            return findAll().stream().filter(x->x.getCompany()==employee.get().getCompany())
                    .map(x-> iManuelEmployeeMapper.toBaseEmployeeDto(x))
                    .collect(Collectors.toList());
        }

    }

    public Boolean createAdvancePayment(AdvancePaymentRequestDto dto) {
        Long authId = jwtTokenManager.validToken(dto.getToken()).get();
        Optional<Employee> employee = employeeRepository.findOptionalByAuthId(authId);
        if (employee.isEmpty()){
            throw new EmployeeException(EErrorType.BAD_REQUEST_ERROR);
        }
        if(dto.getAmount()*3 > employee.get().getSalary()){
            System.out.println("Maasininizin 3 katindan fazla avans çekemezsiniz.");
            return null;
        }

        directProducer.sendAdvanceEmployee(iManuelEmployeeMapper.toEmployeeAdvancePaymentModel(employee.get(),dto));
        return true;
    }

    public Boolean createLeave(LeaveRequestDto dto) {
        Long authId= jwtTokenManager.validToken(dto.getToken()).get();
        Optional<Employee> employee = employeeRepository.findOptionalByAuthId(authId);
        if (employee.isEmpty()){
            throw new EmployeeException(EErrorType.BAD_REQUEST_ERROR);
        }
        directProducer.sendLeaveEmployee(iManuelEmployeeMapper.toEmployeeLeaveModel(employee.get(),dto));
        System.out.println("create leave metodu calisti");
        return true;
    }

    public Boolean createExpnse(ExpenseRequestDto dto) {
        Long authId= jwtTokenManager.validToken(dto.getToken()).get();
        Optional<Employee> employee = employeeRepository.findOptionalByAuthId(authId);
        if (employee.isEmpty()){
            throw new EmployeeException(EErrorType.BAD_REQUEST_ERROR);
        }
        directProducer.sendExpenseEmployee(iManuelEmployeeMapper.toEmployeeExpenseModel(employee.get(), dto));
        System.out.println("create expense metodu calisti");
        return true;
    }
}
