package com.galvanize.services;

import com.galvanize.exception.RecordNotFoundException;
import com.galvanize.jokes.entities.Category;
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

    public List<Joke> findJokesByCategory(Category category) {
        return jokeRepository.findAllByCategory(category);
    }

    public Joke getRandomJoke(Category category) {
        return jokeRepository.findRandomJoke(category);
    }

    public void deleteById(long id) {
        if(jokeRepository.existsById(id)) {
            jokeRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("Joke number "+id+" was not found" );
        }
    }
}
