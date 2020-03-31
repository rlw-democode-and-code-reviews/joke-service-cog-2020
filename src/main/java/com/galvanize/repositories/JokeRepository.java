package com.galvanize.repositories;

import com.galvanize.jokes.entities.Category;
import com.galvanize.jokes.entities.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {
    List<Joke> findAllByJokeContains(String searchString);
    List<Joke> findAllByCategory(Category category);

    @Query(value = "select * from jokes j  where category like ?1 order by RAND() LIMIT 1", nativeQuery = true)
    Joke findRandomJoke(Category category);
}
