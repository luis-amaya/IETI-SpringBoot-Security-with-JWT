package co.edu.eci.ieti.exceptions;

public class UserException extends Exception {

    public static final String USER_CREATE_EXCEPTION = "User already exists.";
    public static final String USER_DOESNOT_EXIST = "The users does not exists.";

    public UserException(String message) {
        super(message);
    }
}
