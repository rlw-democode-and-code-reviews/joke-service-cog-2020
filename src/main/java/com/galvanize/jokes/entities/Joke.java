package com.galvanize.jokes.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "jokes")
public class Joke {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long jokeId;
    Category category;
    String joke;

    public Joke() { }

    public Joke(Category category, String joke) {
        this.category = category;
        this.joke = joke;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "jokeId=" + jokeId +
                ", category=" + category +
                ", joke='" + joke + '\'' +
                '}';
    }

    public Long getJokeId() {
        return jokeId;
    }

    public void setJokeId(Long jokeId) {
        this.jokeId = jokeId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
