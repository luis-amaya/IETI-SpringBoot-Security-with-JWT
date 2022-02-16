package co.edu.eci.ieti.exceptions;

import co.edu.eci.ieti.data.ErrorCodeEnum;
import co.edu.eci.ieti.dto.ServerErrorResponseDto;
import org.springframework.http.HttpStatus;

import javax.ws.rs.InternalServerErrorException;


public class InvalidCredentialsException extends InternalServerErrorException
{
    public InvalidCredentialsException() {
        super(String.valueOf(new ServerErrorResponseDto("User not found", ErrorCodeEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND)));
    }
}
