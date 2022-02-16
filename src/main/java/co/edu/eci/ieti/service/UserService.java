package co.edu.eci.ieti.service;

import co.edu.eci.ieti.data.User;
import co.edu.eci.ieti.dto.UserDto;
import co.edu.eci.ieti.exceptions.UserException;
import java.util.List;

public interface UserService {
    User create(User user) throws UserException;

    User findById(String id) throws UserException;

    List<User> getAll();

    void deleteById(String id) throws UserException;

    User update(UserDto userDto, String userId) throws UserException;
}
