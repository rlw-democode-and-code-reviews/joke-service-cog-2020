package com.galvanize.controllers;

import com.galvanize.exception.RecordNotFoundException;
import com.galvanize.jokes.entities.Category;
import com.galvanize.jokes.entities.Joke;
import com.galvanize.services.JokeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/random")
    public Joke getRandomJoke(@RequestParam(required = false) Category category){
        return jokeService.getRandomJoke(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJokeById(@PathVariable Long id){
        try{
            jokeService.deleteById(id);
            return ResponseEntity.ok("Deleted joke number "+id);
        }catch (RecordNotFoundException e){
            return ResponseEntity.noContent().header("errorMsg", e.getMessage()).build();
        }
    }
}
