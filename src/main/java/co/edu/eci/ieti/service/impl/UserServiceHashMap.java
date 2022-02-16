package co.edu.eci.ieti.service.impl;

import co.edu.eci.ieti.data.User;
import co.edu.eci.ieti.dto.UserDto;
import co.edu.eci.ieti.exceptions.UserException;
import co.edu.eci.ieti.service.UserService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

public class UserServiceHashMap implements UserService {

    HashMap<String, User> usersMap = new HashMap<>();
    private static final AtomicInteger genId = new AtomicInteger(0);
    private static final DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public User create(User user) throws UserException {
        for (User savedUser : usersMap.values()) {
            if (savedUser.equals(user)) {
                throw new UserException(UserException.USER_CREATE_EXCEPTION);
            }
        }
        user.setId(String.valueOf(genId.incrementAndGet()));
        user.setCreatedAt(dateToString());
        usersMap.put(user.getId(), user);

        return user;
    }

    @Override
    public User findById(String id) throws UserException {
        User user = usersMap.get(id);
        if (user == null)
            throw new UserException(UserException.USER_DOESNOT_EXIST);
        return user;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(usersMap.values());
    }

    @Override
    public void deleteById(String id) throws UserException {
        if (usersMap.remove(id) != null)
            throw new UserException(UserException.USER_DOESNOT_EXIST);
    }

    @Override
    public User update(UserDto userDto, String userId) throws UserException {
        User updatedUser = new User();
        if (usersMap.containsKey(userId)) {
            User oldUser = usersMap.get(userId);
            updatedUser.setId(userId);
            updatedUser.setCreatedAt(oldUser.getCreatedAt());
            updatedUser.setName(userDto.getName());
            updatedUser.setLastName(userDto.getLastName());
            updatedUser.setEmail(userDto.getEmail());
            usersMap.put(userId, updatedUser);

        } else {
            throw new UserException(UserException.USER_DOESNOT_EXIST);
        }

        return updatedUser;

    }

    private String dateToString() {
        return date.format(LocalDateTime.now()).toString();
    }

}
