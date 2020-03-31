package com.galvanize.services;

import com.galvanize.jokes.entities.Category;
import com.galvanize.jokes.entities.Joke;
import com.galvanize.repositories.JokeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class JokeServiceTest {

    @Autowired
    JokeService jokeService;

    @MockBean
    JokeRepository jokeRepository;

    List<Joke> testJokes = new ArrayList<>();

    @BeforeEach
    void setUp() {
        assertNotNull(jokeService);

        //Generate Test Data
        for (int i = 0; i <10; i++) {
            if(i %2 == 0){
                testJokes.add(new Joke((long) (i * 1000), Category.DADJOKES, "This is a dad joke number "+i));
            }else{
                testJokes.add(new Joke((long) (i * 2000), Category.TECHNOLOGY, "This is a technology joke number "+i));
            }
        }
    }

    @Test
    void getAllJokes() {
        Mockito.when(jokeRepository.findAll()).thenReturn(testJokes);
        List<Joke> actualJokes = jokeService.getAllJokes();
        assertEquals(testJokes.size(), actualJokes.size());
        assertTrue(testJokes.containsAll(actualJokes));
    }

    @Test
    void findJokesBySearchString() {
        String searchString = "technology joke";
        when(jokeRepository.findAllByJokeContains(searchString))
                .thenReturn(testJokes.stream().filter(j -> j.getJoke().contains(searchString)).collect(Collectors.toList()));

        List<Joke> actualJokes = jokeService.findJokeContaining(searchString);

        assertEquals(5, actualJokes.size());
    }
}