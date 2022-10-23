package com.tiagosora.lab3_1.Repositories;

import com.tiagosora.lab3_1.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.*;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);
}