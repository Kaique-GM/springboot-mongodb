package com.springmongo.mongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmongo.mongo.domain.Post;
import com.springmongo.mongo.repository.PostRepository;
import com.springmongo.mongo.services.Exception.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}