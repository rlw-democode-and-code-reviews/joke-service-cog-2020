package com.galvanize.repositories;

import com.galvanize.jokes.entities.Category;
import com.galvanize.jokes.entities.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {
    List<Joke> findAllByJokeContains(String searchString);
    List<Joke> findAllByCategory(Category category);
}
