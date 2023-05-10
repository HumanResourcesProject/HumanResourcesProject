package com.hrp.mapper;

import com.hrp.dto.request.CreateAdminRequestDto;
import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.repository.entity.Admin;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-10T23:17:06+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class IAdminMapperImpl implements IAdminMapper {

    @Override
    public Admin toAdmin(CreateAdminRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Admin.AdminBuilder<?, ?> admin = Admin.builder();

        admin.name( dto.getName() );
        admin.surname( dto.getSurname() );
        admin.email( dto.getEmail() );
        admin.phone( dto.getPhone() );
        admin.address( dto.getAddress() );

        return admin.build();
    }

    @Override
    public ModelRegisterAdmin toModelRegisterAdmin(Admin admin) {
        if ( admin == null ) {
            return null;
        }

        ModelRegisterAdmin.ModelRegisterAdminBuilder modelRegisterAdmin = ModelRegisterAdmin.builder();

        modelRegisterAdmin.email( admin.getEmail() );
        modelRegisterAdmin.password( admin.getPassword() );

        return modelRegisterAdmin.build();
    }
}
