package co.edu.eci.ieti.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.eci.ieti.data.User;
import co.edu.eci.ieti.dto.UserDto;
import co.edu.eci.ieti.exceptions.UserException;
import co.edu.eci.ieti.repository.UserRepository;

@Service
public class UserServiceMongoDB implements UserService {

    private final UserRepository userRepository;
    private static final DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public UserServiceMongoDB(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) throws UserException {
        User userTst = null;
        try {
            userTst = findByEmail(user.getEmail());
        }catch (UserException e){
                user.setCreatedAt(dateToString());
                return userRepository.save(user);
        }
        throw new UserException(UserException.USER_CREATE_EXCEPTION);
    }

    @Override
    public User findById(String id) throws UserException {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id).get();
        } else {
            throw new UserException(UserException.USER_DOESNOT_EXIST);
        }

    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(String id) throws UserException {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserException(UserException.USER_DOESNOT_EXIST);
        }

    }

    @Override
    public User update(UserDto userDto, String userId) throws UserException {
        User actualUser = new User();
        if (userRepository.existsById(userId)) {
            actualUser = userRepository.findById(userId).get();
            actualUser.setCreatedAt(actualUser.getCreatedAt());
            actualUser.setEmail(userDto.getEmail());
            actualUser.setLastName(userDto.getLastName());
            actualUser.setName(userDto.getName());
            userRepository.save(actualUser);
        } else {
            throw new UserException(UserException.USER_DOESNOT_EXIST);
        }

        return actualUser;
    }

    @Override
    public User findByEmail(String email) throws UserException {
        List<User> users = userRepository.findAll();
        User userFinded = null;
        for (User user: users){
            if(user.getEmail().equals(email)){
                userFinded = user;
                break;
            }
        }
        if (userFinded == null) {
            throw new UserException(UserException.USER_DOESNOT_EXIST);
        }else {
            return userFinded;
        }
    }

    private String dateToString() {
        return date.format(LocalDateTime.now()).toString();
    }


}
