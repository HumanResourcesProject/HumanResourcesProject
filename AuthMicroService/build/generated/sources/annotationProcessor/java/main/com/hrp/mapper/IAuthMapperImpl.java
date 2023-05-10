package com.hrp.mapper;

import com.hrp.dto.request.RegisterAdminRequestDto;
import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-10T18:17:46+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class IAuthMapperImpl implements IAuthMapper {

    @Override
    public RegisterAdminRequestDto toRegisterAdminRequestDto(ModelRegisterAdmin model) {
        if ( model == null ) {
            return null;
        }

        RegisterAdminRequestDto.RegisterAdminRequestDtoBuilder registerAdminRequestDto = RegisterAdminRequestDto.builder();

        registerAdminRequestDto.email( model.getEmail() );
        registerAdminRequestDto.password( model.getPassword() );

        return registerAdminRequestDto.build();
    }

    @Override
    public Auth toAuth(RegisterAdminRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder<?, ?> auth = Auth.builder();

        auth.password( dto.getPassword() );
        auth.email( dto.getEmail() );

        return auth.build();
    }
}
