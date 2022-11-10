package com.onevizion.onevizionchallenge.services;

import com.onevizion.onevizionchallenge.dtos.BookDto;
import com.onevizion.onevizionchallenge.dtos.AuthorOccurencesResponseDto;
import com.onevizion.onevizionchallenge.models.Book;

import java.util.List;
import java.util.Map;

public interface BookService {

    public List<Book> getAllBooks();

    public void saveBook(BookDto holder);

    public Map<String, List<Book>> getAllBooksGroupedByAuthors();

    public List<AuthorOccurencesResponseDto> getAllBooksBasedOnSymbolRelevance(String symbol);
}
