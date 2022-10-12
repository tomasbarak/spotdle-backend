package com.spotdle.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spotdle.models.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, String>{
    
}
