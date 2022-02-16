package co.edu.eci.ieti.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.eci.ieti.data.User;

public interface UserRepository extends MongoRepository<User, String> {

}
