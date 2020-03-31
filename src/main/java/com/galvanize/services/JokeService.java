package com.galvanize.services;

import com.galvanize.jokes.entities.Joke;
import com.galvanize.repositories.JokeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JokeService {

    JokeRepository jokeRepository;

    public JokeService(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    public List<Joke> findJokeContaining(String searchString) {
        return jokeRepository.findAllByJokeContains(searchString);
    }
}
