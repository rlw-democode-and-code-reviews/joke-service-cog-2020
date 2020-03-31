package com.galvanize.jokes.entities;

import javax.persistence.*;

@Entity
@Table(name = "jokes")
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long jokeId;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    Category category;
    String joke;

    public Joke() { }

    public Joke(Long jokeId, Category category, String joke){
        this.jokeId = jokeId;
        this.category = category;
        this.joke = joke;
    }

    public Joke(Category category, String joke){
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
