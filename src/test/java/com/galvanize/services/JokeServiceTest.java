package com.galvanize.services;

import com.galvanize.exception.RecordNotFoundException;
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
import static org.mockito.Mockito.verify;

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

    @Test
    void findJokesByCategory() {
        when(jokeRepository.findAllByCategory(Category.DADJOKES))
                .thenReturn(testJokes.stream().filter(j -> j.getCategory().equals(Category.DADJOKES)).collect(Collectors.toList()));

        List<Joke> actualJokes = jokeService.findJokesByCategory(Category.DADJOKES);

        assertEquals(5, actualJokes.size());
    }

    @Test
    void getRandomJoke_byCategory() {
        when(jokeRepository.findRandomJoke(Category.TECHNOLOGY)).thenReturn(testJokes.get(1));

        Joke actualJoke = jokeService.getRandomJoke(Category.TECHNOLOGY);

        assertNotNull(actualJoke);
    }

    @Test
    void deleteById() {
        jokeService.deleteById(1000L);
        verify(jokeRepository).deleteById(1000L);
    }

    @Test
    void deleteById_notExist_throwsException() {
        when(jokeRepository.existsById(1000L)).thenReturn(false);

        assertThrows(RecordNotFoundException.class, () -> jokeService.deleteById(1000L));
    }
}