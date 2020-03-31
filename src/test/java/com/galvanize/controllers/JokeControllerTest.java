package com.galvanize.controllers;

import com.galvanize.jokes.entities.Category;
import com.galvanize.jokes.entities.Joke;
import com.galvanize.repositories.JokeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JokeControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    JokeController controller;

    @MockBean
    JokeRepository jokeRepository;

    List<Joke> testJokes = new ArrayList<>();

    @BeforeEach
    void setUp() {
        assertNotNull(mvc);
        assertNotNull(controller);

        //Generate Test Data
        for (int i = 0; i <10; i++) {
            if(i %2 == 0){
                testJokes.add(new Joke((long) (i * 1000), Category.DADJOKES, "This is a dad joke number "+i));
            }else{
                testJokes.add(new Joke((long) (i * 2000), Category.TECHNOLOGY, "This is a technology joke number "+i));
            }
        }

    }

//    POST: new joke - accept any joke in one of the specified categories
//    GET: all jokes in db

    @Test
    void getAllJokes() throws Exception {
        when(jokeRepository.findAll()).thenReturn(testJokes);

        mvc.perform(get("/api/jokes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));

    }

//    GET: all jokes containing a given search string (bonus: add optional category to search)
    @Test
    void searchJokesByString() throws Exception {
        String searchString = "technology joke";
        when(jokeRepository.findAllByJokeContains(searchString))
                .thenReturn(testJokes.stream().filter(j -> j.getJoke().contains(searchString)).collect(Collectors.toList()));

        mvc.perform(get("/api/jokes/search").param("searchString", searchString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

//    GET: all jokes by category
    @Test
    void getJokesByCategory() throws Exception {
        when(jokeRepository.findAllByCategory(Category.DADJOKES))
                .thenReturn(testJokes.stream().filter(j -> j.getCategory().equals(Category.DADJOKES)).collect(Collectors.toList()));

        mvc.perform(get("/api/jokes/category").param("category", Category.DADJOKES.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

//    GET: random joke by optional category (see sql below)
//    PATCH: update the category, or text of a joke
//    DELETE: delete a joke by id


}