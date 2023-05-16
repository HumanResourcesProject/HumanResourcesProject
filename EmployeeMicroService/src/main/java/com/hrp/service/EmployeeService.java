package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrp.dto.request.BaseEmployeeRequestDto;
import com.hrp.dto.request.EmployeeUpdateRequestDto;
import com.hrp.dto.request.requirements.ExpenseRequestDto;
import com.hrp.dto.request.requirements.AdvancePaymentRequestDto;
import com.hrp.dto.request.requirements.LeaveRequestDto;
import com.hrp.dto.response.BaseEmployeeResponseDto;
import com.hrp.dto.response.MyLeaveResponseDto;
import com.hrp.exception.EErrorType;
import com.hrp.exception.EmployeeException;
import com.hrp.mapper.IManuelEmployeeMapper;
import com.hrp.rabbitmq.model.ModelEmployeeExpense;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.rabbitmq.model.ModelTurnAllLeaveRequest;
import com.hrp.rabbitmq.producer.DirectProducer;
import com.hrp.repository.IEmployeeRepository;
import com.hrp.repository.entity.Employee;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import com.hrp.utility.StaticValues;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
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
        System.out.println("asdsadas");
        Long authId= jwtTokenManager.validToken(dto.getToken()).get();
        Optional<Employee> employee = employeeRepository.findOptionalByAuthId(authId);
        if (employee.isEmpty()){
            throw new EmployeeException(EErrorType.BAD_REQUEST_ERROR);
        }
        ModelEmployeeExpense modelEmployeeExpense=new ModelEmployeeExpense();
        modelEmployeeExpense = iManuelEmployeeMapper.toEmployeeExpenseModel(employee.get(),dto);
        modelEmployeeExpense.setInvoiceUrl(toTurnStringAvatar(dto.getInvoiceUrl()));
        directProducer.sendExpenseEmployee(modelEmployeeExpense);
        System.out.println("create expense metodu calisti");
        return true;
    }

    public Boolean updateEmployee(EmployeeUpdateRequestDto dto) {
        Optional<Long> authId = jwtTokenManager.validToken(dto.getToken());
        Optional<Employee> employee = employeeRepository.findOptionalByAuthId(authId.get());
        Employee newEmployee=iManuelEmployeeMapper.toEmployee(employee.get(),dto);
        System.out.println("********************************************");
        System.out.println("eski employe bilgileri"+employee.get().toString());
        System.out.println("----------*****************************-----------------");
        System.out.println("new employee bilgileri"+newEmployee.toString());
        //newEmployee.setAvatar(toTurnStringAvatar(dto.getAvatar()));
        System.out.println("116 da ki avatar cevirmeden sonra new employee bilgileri"+newEmployee.toString());
        System.out.println("********************************************");
        update(newEmployee);
return true;
    }



    private String toTurnStringAvatar(MultipartFile file) {
        Map config = new HashMap();
        config.put("cloud_name", "doqksh0xh");
        config.put("api_key", "871216635594134");
        config.put("api_secret", "6b3zcRZyWKeuiW6qIq4XvWnhVno");
        Cloudinary cloudinary = new Cloudinary(config);
        try{
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            System.out.println(url+" --------------------------");
            return url;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<BaseEmployeeResponseDto> findAllEmployee(BaseEmployeeRequestDto dto) {
        Optional<Long> authId = jwtTokenManager.validToken(dto.getToken());
        Optional<Employee> employee = employeeRepository.findOptionalByAuthId(authId.get());
        return findAll().stream().filter(x->x.getCompany()==employee.get().getCompany())
                .map(x-> iManuelEmployeeMapper.toBaseEmployeeDto(x))
                .collect(Collectors.toList());
    }

    public BaseEmployeeResponseDto findMe(BaseEmployeeRequestDto dto) {
        Optional<Long> authId = jwtTokenManager.validToken(dto.getToken());
        Optional<Employee> employee = employeeRepository.findOptionalByAuthId(authId.get());
        return iManuelEmployeeMapper.toBaseEmployeeDto(employee.get());
    }

    public List<ModelTurnAllLeaveRequest> myLeaveFindAll(BaseEmployeeRequestDto dto) {
        Optional<Long> authId = jwtTokenManager.validToken(dto.getToken());
        Optional<Employee> employee = employeeRepository.findOptionalByAuthId(authId.get());
        directProducer.sendMyLeaveFindAll(ModelEmployeeLeave.builder()
                        .employeeId(employee.get().getId())
                .build());
        System.out.println("service ici "+ 166);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("service ici "+ 173);
        List<ModelTurnAllLeaveRequest> responses= new ArrayList<>();
        System.out.println("service ici "+ 175);
        for (ModelTurnAllLeaveRequest response : StaticValues.myLeaveFindAll){
            System.out.println("service ici "+ 177);
            responses.add(response);
        }
        System.out.println("00000099999900000000000");
        System.out.println("00000099999900000000000");
        System.out.println("00000099999900000000000");
        System.out.println("00000099999900000000000");
        System.out.println(responses);
        return responses;
    }

}
