package com.onevizion.onevizionchallenge.repositories;

import com.onevizion.onevizionchallenge.models.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();

    Book findOne(Integer id);

    void save(Book person);

    int delete(Integer id);

}
