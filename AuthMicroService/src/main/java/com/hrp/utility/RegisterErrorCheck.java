package com.hrp.utility;

import com.hrp.exception.AuthException;
import com.hrp.exception.EErrorType;
import org.springframework.web.multipart.MultipartFile;

public class RegisterErrorCheck {
    private RegisterErrorCheck() {
        throw new IllegalStateException("register error class error... ");
    }

    public static void registerErrorInController(String email, String name, String surname, String phone){
        if (email.trim().isEmpty()  ) throw new AuthException(EErrorType.E_MAIL_NOT_EMPTY);
        if (name.trim().isEmpty() ) throw new AuthException(EErrorType.NAME_NOT_EMPTY);
        if (surname.trim().isEmpty() ) throw new AuthException(EErrorType.SURNAME_NOT_EMPTY);
        if (phone.trim().isEmpty() ) throw new AuthException(EErrorType.PHONE_NUMBER_NOT_EMPTY);
    }
}
