package com.springmongo.mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmongo.mongo.domain.User;
import com.springmongo.mongo.dto.UserDTO;
import com.springmongo.mongo.repository.UserRepository;
import com.springmongo.mongo.services.Exception.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User obj) {
        return repo.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public User update(User obj) {
        Optional<User> newObj = repo.findById(obj.getId());
        User entity = newObj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
        updateData(entity, obj);
        return repo.save(entity);
    }

    public User fromDTO(UserDTO objDTO) {
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }

    public void updateData(User newObj, User obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}