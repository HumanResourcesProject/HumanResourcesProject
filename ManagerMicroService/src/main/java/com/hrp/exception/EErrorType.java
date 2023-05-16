package com.hrp.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {
    INTERNAL_ERROR(3000,"Unexpected error on server",INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(4001,"Invalid token information",BAD_REQUEST),
    BAD_REQUEST_ERROR(1202,"You have entered an invalid parameter",BAD_REQUEST),
    COMPANY_MANAGER_NOT_FOUND(1303,"Company manager cannot be found",HttpStatus.BAD_REQUEST),
    USER_NOT_ACTIVE(1304,"User is not active",HttpStatus.BAD_REQUEST),
    USER_NOT_LOGIN(1305,"User is not login",HttpStatus.BAD_REQUEST),
    USER_NOT_PERMISSION(1306,"User is not permission",HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(1307,"User is not exist",HttpStatus.BAD_REQUEST),
    USER_NOT_REGISTER(1308,"User is not register",HttpStatus.BAD_REQUEST),
    USER_NOT_UPDATE(1309,"User is not update",HttpStatus.BAD_REQUEST),
    USER_NOT_DELETE(1310,"User is not delete",HttpStatus.BAD_REQUEST),
    USER_NOT_CREATE(1311,"User is not create",HttpStatus.BAD_REQUEST),
    USER_NOT_RESET_PASSWORD(1312,"User is not reset password",HttpStatus.BAD_REQUEST),
    USER_NOT_CHANGE_PASSWORD(1313,"User is not change password",HttpStatus.BAD_REQUEST),
    USER_NOT_CHANGE_USERNAME(1314,"User is not change username",HttpStatus.BAD_REQUEST),
    USERNAME_ERROR(1302,"Username already registered",HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_EMPTY(1001,"Password cannot be empty",HttpStatus.BAD_REQUEST),
    PHONE_NOT_LETTER(1004,"Phone must be number",HttpStatus.BAD_REQUEST),
    USERID_NOT_EMPTY(1002,"UserId cannot be empty",HttpStatus.BAD_REQUEST),
    FIELD_NOT_EMPTY(1003,"Field cannot be empty",HttpStatus.BAD_REQUEST),
    EMAIL_NOT_EMPTY(1005,"Email cannot be empty",HttpStatus.BAD_REQUEST),
    USER_NOT_EMPTY(1006,"User cannot be empty",HttpStatus.BAD_REQUEST),
    TOKEN_NOT_FOUND(4002,"Token not found",BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
