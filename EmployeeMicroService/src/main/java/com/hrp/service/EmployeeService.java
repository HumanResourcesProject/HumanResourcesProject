package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrp.dto.request.BaseEmployeeRequestDto;
import com.hrp.dto.request.EmployeeUpdateRequestDto;
import com.hrp.dto.request.requirements.ExpenseRequestDto;
import com.hrp.dto.request.requirements.AdvancePaymentRequestDto;
import com.hrp.dto.request.requirements.LeaveRequestDto;
import com.hrp.dto.response.BaseEmployeeResponseDto;
import com.hrp.exception.EErrorType;
import com.hrp.exception.EmployeeException;
import com.hrp.mapper.IManuelEmployeeMapper;
import com.hrp.rabbitmq.model.*;
import com.hrp.rabbitmq.producer.DirectProducer;
import com.hrp.repository.IEmployeeRepository;
import com.hrp.repository.entity.Employee;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.ArrayList;
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
        Employee employee= iManuelEmployeeMapper.ToEmployee(model);
        employee.setSalary(5000L);
        employee.setAvatar("https://gcavocats.ca/wp-content/uploads/2018/09/man-avatar-icon-flat-vector-19152370-1.jpg");
        save(employee);
    }


    public Boolean createAdvancePayment(AdvancePaymentRequestDto dto) {
        Long authId = jwtTokenManager.validToken(dto.getToken()).get();
        Optional<Employee> employee = employeeRepository.findOptionalByAuthId(authId);
        if (employee.isEmpty()){
            throw new EmployeeException(EErrorType.BAD_REQUEST_ERROR);
        }
        // gereksiz para kontrolü önde yapiliyor
        if(dto.getAmount() > employee.get().getSalary()*3){
            System.out.println("Maasininizin 3 katindan fazla avans çekemezsiniz.");
            return null;
        }

        System.out.println("advance payment metodu model ici:"+ iManuelEmployeeMapper.toEmployeeAdvancePaymentModel(employee.get(),dto));
        directProducer.sendAdvanceEmployee(iManuelEmployeeMapper.toEmployeeAdvancePaymentModel(employee.get(),dto));
        return true;
    }

    public Boolean createLeave(LeaveRequestDto dto) {
        Long authId= jwtTokenManager.validToken(dto.getToken()).get();
        Optional<Employee> employee = employeeRepository.findOptionalByAuthId(authId);
        if (employee.isEmpty()){
            throw new EmployeeException(EErrorType.BAD_REQUEST_ERROR);
        }
        System.out.println("create leave metodu model ici: "+iManuelEmployeeMapper.toEmployeeLeaveModel(employee.get(),dto));
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
        System.out.println("createExpnse metodu modelin ici: "+ modelEmployeeExpense.toString());
        directProducer.sendExpenseEmployee(modelEmployeeExpense);
        System.out.println("create expense metodu calisti");
        return true;
    }

    public Boolean updateEmployee(EmployeeUpdateRequestDto dto) {
        Optional<Long> authId = jwtTokenManager.validToken(dto.getToken());
        Optional<Employee> employee = employeeRepository.findOptionalByAuthId(authId.get());
        Employee newEmployee=iManuelEmployeeMapper.toEmployee(employee.get(),dto);
        newEmployee.setAvatar(toTurnStringAvatar(dto.getAvatar()));
        update(newEmployee);
return true;
    }
    public Boolean updateEmployeeNoPhoto(EmployeeUpdateRequestDto dto) {
        Optional<Long> authId = jwtTokenManager.validToken(dto.getToken());
        Optional<Employee> employee = employeeRepository.findOptionalByAuthId(authId.get());
        Employee newEmployee=iManuelEmployeeMapper.toEmployee(employee.get(),dto);
        //newEmployee.setAvatar(toTurnStringAvatar(dto.getAvatar()));
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
    public List<BaseEmployeeResponseDto> findAllMyEmployee(BaseEmployeeRequestDto dto) {
        Optional<Long> authId = jwtTokenManager.validToken(dto.getToken());
        Optional<Employee> employee = employeeRepository.findOptionalByAuthId(authId.get());
        System.out.println("bu find all stream dır.... "+findAll().stream().filter(x->x.getCompany().equals(employee.get().getCompany()))
                .map(x-> iManuelEmployeeMapper.toBaseEmployeeDto(x))
                .collect(Collectors.toList()));
        return findAll().stream().filter(x->x.getCompany().equals(employee.get().getCompany()))
                .map(x-> iManuelEmployeeMapper.toBaseEmployeeDto(x))
                .collect(Collectors.toList());
    }
    public BaseEmployeeResponseDto findMe(BaseEmployeeRequestDto dto) {
        Optional<Long> authId = jwtTokenManager.validToken(dto.getToken());
        Optional<Employee> employee = employeeRepository.findOptionalByAuthId(authId.get());
        return iManuelEmployeeMapper.toBaseEmployeeDto(employee.get());
    }
    public void findAllMyEmployeeForManager(ModelBaseEmployee model) {
        Optional<List<Employee>> employees = employeeRepository.findOptionalByCompany(model.getCompany());
        Optional<List<ModelBaseEmployee>> modelEmployess= Optional.of(new ArrayList<>());
        for (Employee employe: employees.get()){
            modelEmployess.get().add(iManuelEmployeeMapper.toModel(employe));
        }
        System.out.println("geri dönüste producer a gelmeden hemen önce 151 servis");
        directProducer.sendEmployeeListForManager(modelEmployess.get());
    }
}
