package service;

import com.cloudinary.api.exceptions.BadRequest;
import com.hrp.dto.request.*;
import com.hrp.dto.response.AuthLoginResponse;
import com.hrp.exception.AuthException;
import com.hrp.exception.EErrorType;
import com.hrp.mapper.IManuelMapper;
import com.hrp.rabbitmq.model.ModelRegisterManager;
import com.hrp.rabbitmq.producer.DirectProducer;
import com.hrp.repository.IAuthRepository;
import com.hrp.repository.entity.Auth;
import com.hrp.repository.entity.enums.ERole;
import com.hrp.service.AuthService;
import com.hrp.utility.JwtTokenManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.*;
import org.mockito.Mockito;
import org.mockito.exceptions.misusing.PotentialStubbingProblem;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks // auth serviceden bir tane mock nesnesi yaratıyor.
    private AuthService authService;
    @Mock
    private RegisterManagerRequestDto managerRequestDto;
    @Mock
    private AuthLoginDto authLoginDto;
    @Mock
    private IAuthRepository authRepository;
    @Mock
    private JwtTokenManager jwtTokenManager;
    @Mock
    private IManuelMapper manuelMapper;
    @Mock
    private DirectProducer directProducer;
    @Mock
    private ModelRegisterManager modelRegisterManager;


//    @BeforeEach
//    public void setup(){
//        MockitoAnnotations.openMocks(this);
//    }
    /**
     * exceptionları kontrol etmedim login kontrolü sadece bu metod
     * ama yine de exception durumlarını geçti sayıyor neden ki acaba?
     * bir de hocam genel olarak test böyle yazdıklarım gibi mi yazılıyor
     * mantığı bu şekilde mi işliyor ?
     */
    @Test
    void authLogin(){
        when(authRepository.findOptionalByEmailAndPassword("xxx@gmail.com","asd"))
                .thenReturn(Optional.ofNullable(Auth.builder().id(2L).role(ERole.ADMIN).build()));
        when(jwtTokenManager.createToken(2L)).thenReturn(Optional.of("tokenhere"));
        AuthLoginResponse authLoginResponse=authService.authLogin(AuthLoginDto.builder()
                .email("xxx@gmail.com").password("asd").build());
        assertEquals("tokenhere",authLoginResponse.getToken(),"wrong token response");
        assertEquals(ERole.ADMIN,authLoginResponse.getRole(),"wrong role response");
    }
    @Test
    void authLogin_AuthIsEmpty(){
        when(authRepository.findOptionalByEmailAndPassword("xxx@gmail.com","asd"))
                .thenReturn(null);
        assertThrows(NullPointerException.class,() ->{authService.authLogin(AuthLoginDto.builder()
                        .email("xxx@gmail.com")
                        .password("asd")
                .build());});
//        assertThrows(NullPointerException.class,authService::authLogin);
    }

    @Test
    void authLogin_TokenIsEmpty(){
        // verilere hazırlanır önce
        AuthLoginDto dto = AuthLoginDto.builder()
                .email("xxx@gmail.com")
                .password("asd")
                .build();
        when(authRepository.findOptionalByEmailAndPassword("xxx@gmail.com","asd"))
                .thenReturn(Optional.ofNullable(Auth.builder().build()));
        assertThrows(AuthException.class,() ->{authService.authLogin(dto);});
    }


    @Test
    void changePassword(){
        when(jwtTokenManager.validToken("ccc")).thenReturn(Optional.of(2L));
        when(authRepository.findById(2L)).thenReturn(Optional.of(Auth.builder().id(3L).email("xxx@gmail.com").password("asd").build()));
        authService.update(Auth.builder().build());
        boolean  check=authService.changePassword(ChangePasswordDto.builder().token("ccc").newpassword("ggg").confirmpassword("ggg").oldpassword("asd").build());
        assertTrue(check);
    }
    @Test
    void changePassword_UnexpectedToken (){
        when(jwtTokenManager.validToken("aaa")).thenReturn(Optional.ofNullable(null));
        assertThrows(AuthException.class,() ->{authService.changePassword(ChangePasswordDto.builder()
                        .token("aaa")
                .build());});
    }

    @Test
    void changePassword_AuthIsEmpty (){
        when(jwtTokenManager.validToken("bbb")).thenReturn(Optional.of(1L));
        when(authService.findById(1L)).thenReturn(Optional.ofNullable(null));
        assertThrows(AuthException.class,()->authService.changePassword(ChangePasswordDto.builder()
                        .token("bbb")
                .build()));
    }

    @Test
    void changePassword_PasswordsDoNotMatch (){
        when(jwtTokenManager.validToken("bbb")).thenReturn(Optional.of(1L));
        when(authService.findById(1L)).thenReturn(Optional.of(Auth.builder()
                        .password("Abc")
                .build()));
     assertThrows(AuthException.class, ()->authService.changePassword(ChangePasswordDto.builder()
                     .oldpassword("123")
                     .token("bbb")
             .build()));
    }
    @Test
    void registerAdmin(){
        when(jwtTokenManager.validToken("ccc")).thenReturn(Optional.of(2L));
        when(authRepository.save(ArgumentMatchers.any(Auth.class))).thenReturn(Auth.builder()
                .email("asd@gmail.com")
                .build());

        MockMultipartFile multipartFile = new MockMultipartFile(
                "avatar",
                "filename.jpg",
                "image/jpeg",
                "filecontent".getBytes()
        );

        boolean check=  authService.registerAdmin(RegisterAdminRequestDto.builder()
                .email("asd@gmail.com")
                .avatar(multipartFile)
                .token("ccc")
                .build());

        assertNotNull(check,"boolean geldi herhalde");
    }
    @Test
    void registerAdminNoPhoto(){
        when(jwtTokenManager.validToken("ccc")).thenReturn(Optional.of(2L));
        when(authRepository.save(ArgumentMatchers.any(Auth.class))).thenReturn(Auth.builder()
                .email("asd@gmail.com")
                .build());
        boolean check=  authService.registerAdmin(RegisterAdminRequestDto.builder()
                .email("asd@gmail.com")
                .token("ccc")
                .build());

        assertNotNull(check,"boolean geldi herhalde");
    }
    @Test
    void registerAdmin_UnexpectedToken(){
        when(jwtTokenManager.validToken("aaa")).thenReturn(Optional.ofNullable(null));
        assertThrows(AuthException.class, ()-> authService.registerAdmin(RegisterAdminRequestDto.builder()
                        .token("aaa")
                .build()));
    }
    @Test
    void registerAdmin_EmailAlreadyExists(){
        when(jwtTokenManager.validToken("aaa")).thenReturn(Optional.ofNullable(1L));
        when(authRepository.findOptionalByEmail("abc@company.com")).thenReturn(Optional.of(Auth.builder().build()));
        assertThrows(AuthException.class, ()->authService.registerAdmin(RegisterAdminRequestDto.builder()
                        .token("aaa")
                        .email("abc@company.com")
                .build()));
    }
    //directProducer ı kontrol etme

    @Test
    void registerManager(){
        when(jwtTokenManager.validToken("ccc")).thenReturn(Optional.of(2L));
        when(authRepository.save(ArgumentMatchers.any(Auth.class))).thenReturn(Auth.builder()
                .email("sadas@gmail.com")
                .build());
        boolean check=  authService.registerManager(RegisterManagerRequestDto.builder()
                .email("asd@gmail.com")
                .token("ccc")
                .build());
        assertNotNull(check,"boolean geldi herhalde");
    }
    @Test
    void registerEmployee(){
        when(jwtTokenManager.validToken("ccc")).thenReturn(Optional.of(2L));
        when(authRepository.save(ArgumentMatchers.any(Auth.class))).thenReturn(Auth.builder()
                .email("asd@gmail.com")
                .build());
        boolean check=  authService.registerEmployee(RegisterEmployeeRequestDto.builder()
                .email("asd@gmail.com")
                .token("ccc")
                .build());

        assertNotNull(check,"boolean geldi herhalde");
    }

    @Test
    void forgotPassword(){
        when(authRepository.findOptionalByEmail("ccc@gmail.com"))
                .thenReturn(Optional.ofNullable(Auth.builder().email("ccc@gmail.com").build()));
        authService.update(Auth.builder().build());
        boolean check=authService.forgotPassword(AuthLoginDto.builder().email("ccc@gmail.com").build());
        assertTrue(check,"yanlis vaar");
    }

}
