package com.onevizion.onevizionchallenge.services;

import com.onevizion.onevizionchallenge.dtos.BookDto;
import com.onevizion.onevizionchallenge.dtos.AuthorOccurencesResponseDto;
import com.onevizion.onevizionchallenge.models.Book;
import com.onevizion.onevizionchallenge.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.onevizion.onevizionchallenge.utils.ComparatorUtils.getComparator;

@Service
@AllArgsConstructor
public class BooksServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ConversionService conversionService;

    public List<Book> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream()
                .sorted(getComparator(Book.Fields.title))
                .collect(Collectors.toList());
    }

    public void saveBook(BookDto holder) {
        Book newBook = conversionService.convert(holder, Book.class);
        bookRepository.save(newBook);
    }

    public Map<String, List<Book>> getAllBooksGroupedByAuthors() {
        Function<Book, String> func = Book::getAuthor;
        return getAllBooksGroupedBy(func);
    }

    public List<AuthorOccurencesResponseDto> getAllBooksBasedOnSymbolRelevance(String symbol) {

        List<AuthorOccurencesResponseDto> result = new ArrayList<>();
        String symbolToLookFor = symbol.toLowerCase();
        List<Book> bookList = bookRepository.findAll();

        Map<String, List<Book>> collect = bookList.stream()
                .filter(item -> {
                    return item.getTitle().toLowerCase().contains(symbolToLookFor);
                })
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.mapping(Function.identity(), Collectors.toList())));

        List<List<Book>> listGroupedByAuthorsAndContainsSymbol = new ArrayList<>(collect.values());

        for (List<Book> authorWithContent : listGroupedByAuthorsAndContainsSymbol) {
            Integer totalOccurencePerAuthor = authorWithContent.stream()
                    .map(item -> StringUtils.countOccurrencesOf(item.getTitle().toLowerCase(), symbolToLookFor))
                    .reduce(0, Integer::sum);
            AuthorOccurencesResponseDto authorWithData = AuthorOccurencesResponseDto.builder()
                    .author(authorWithContent.get(0).getAuthor())
                    .occurrence(totalOccurencePerAuthor)
                    .build();
            result.add(authorWithData);
        }

        // first 10 will go
        List<AuthorOccurencesResponseDto> onlyFirstTenWillGo = result
                .stream()
                .sorted(Comparator.comparing(AuthorOccurencesResponseDto::getOccurrence).reversed())
                .limit(10)
                .collect(Collectors.toList());

        return onlyFirstTenWillGo;
    }


    private <T> Map<T, List<Book>> getAllBooksGroupedBy(Function<Book, T> groupItem) {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream()
                .collect(Collectors.groupingBy(groupItem, Collectors.mapping(Function.identity(), Collectors.toList())));
    }

}
