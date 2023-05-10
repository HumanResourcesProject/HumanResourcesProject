package com.hrp.mapper;

import com.hrp.dto.response.CompanyManagerFindAllResponseDto;
import com.hrp.repository.entity.CompanyManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-10T22:47:24+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class ICompanyManagerMapperImpl implements ICompanyManagerMapper {

    @Override
    public CompanyManagerFindAllResponseDto toCompanyManagerFindAllResponseDto(CompanyManager companyManager) {
        if ( companyManager == null ) {
            return null;
        }

        CompanyManagerFindAllResponseDto.CompanyManagerFindAllResponseDtoBuilder<?, ?> companyManagerFindAllResponseDto = CompanyManagerFindAllResponseDto.builder();

        companyManagerFindAllResponseDto.companyManagerId( companyManager.getId() );
        companyManagerFindAllResponseDto.createDate( companyManager.getCreateDate() );
        companyManagerFindAllResponseDto.state( companyManager.isState() );
        companyManagerFindAllResponseDto.name( companyManager.getName() );
        companyManagerFindAllResponseDto.middleName( companyManager.getMiddleName() );
        companyManagerFindAllResponseDto.surname( companyManager.getSurname() );
        try {
            if ( companyManager.getDateOfBirth() != null ) {
                companyManagerFindAllResponseDto.dateOfBirth( new SimpleDateFormat().parse( companyManager.getDateOfBirth() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        companyManagerFindAllResponseDto.email( companyManager.getEmail() );
        companyManagerFindAllResponseDto.address( companyManager.getAddress() );
        companyManagerFindAllResponseDto.phone( companyManager.getPhone() );
        companyManagerFindAllResponseDto.company( companyManager.getCompany() );
        companyManagerFindAllResponseDto.job( companyManager.getJob() );
        companyManagerFindAllResponseDto.department( companyManager.getDepartment() );
        if ( companyManager.getJobStart() != null ) {
            companyManagerFindAllResponseDto.jobStart( LocalDateTime.parse( companyManager.getJobStart() ) );
        }
        if ( companyManager.getJobEnd() != null ) {
            companyManagerFindAllResponseDto.jobEnd( LocalDateTime.parse( companyManager.getJobEnd() ) );
        }
        companyManagerFindAllResponseDto.avatar( companyManager.getAvatar() );

        return companyManagerFindAllResponseDto.build();
    }
}
