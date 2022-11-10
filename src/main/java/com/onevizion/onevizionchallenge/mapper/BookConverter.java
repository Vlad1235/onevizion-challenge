package com.onevizion.onevizionchallenge.mapper;

import com.onevizion.onevizionchallenge.dtos.BookDto;
import com.onevizion.onevizionchallenge.models.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;


@Component
public class BookConverter implements Converter<BookDto, Book> {

    @Override
    public Book convert(BookDto source) {
        return Book.builder()
                .id(ThreadLocalRandom.current().nextInt(6, Integer.MAX_VALUE))  //todo не понятно по требованиям можно ли менять ключ Primary Key. Принято решение вручную генерить ключи начиная от 6
                .title(source.getTitle())
                .author(source.getAuthor())
                .description(source.getDescription())
                .build();
    }
}
