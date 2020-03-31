package com.galvanize.controllers;

import com.galvanize.jokes.entities.Category;
import com.galvanize.jokes.entities.Joke;
import com.galvanize.services.JokeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jokes")
public class JokeController {

    JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping
    public List<Joke> getAllJokes(){
        return jokeService.getAllJokes();
    }

    @GetMapping("/search")
    public List<Joke> getJokesContaining(@RequestParam String searchString){
        return jokeService.findJokeContaining(searchString);
    }

    @GetMapping("/category")
    public List<Joke> getJokesByCategory(@RequestParam Category category){
        return jokeService.findJokesByCategory(category);
    }
}
