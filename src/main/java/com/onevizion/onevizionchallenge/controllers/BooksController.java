package com.onevizion.onevizionchallenge.controllers;


import com.onevizion.onevizionchallenge.dtos.BookDto;
import com.onevizion.onevizionchallenge.dtos.AuthorOccurencesResponseDto;
import com.onevizion.onevizionchallenge.models.Book;
import com.onevizion.onevizionchallenge.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@RestController
public class BooksController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> findAllItems() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("/book")
    public ResponseEntity<Void> saveItem(@Valid @RequestBody BookDto request) {
        bookService.saveBook(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-books-grouped-by-author")
    public ResponseEntity<Map<String, List<Book>>> findAllItemsGroupedByAuthor() {
        return ResponseEntity.ok(bookService.getAllBooksGroupedByAuthors());
    }

    @GetMapping("/find-authors-occurrences-by-symbol")
    public ResponseEntity<List<AuthorOccurencesResponseDto>> findAllItemsBasedOnSymbolRelevance(@RequestParam String symbol) {
        return ResponseEntity.ok(bookService.getAllBooksBasedOnSymbolRelevance(symbol));
    }

}
