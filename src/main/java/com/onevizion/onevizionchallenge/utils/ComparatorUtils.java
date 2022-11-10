package com.onevizion.onevizionchallenge.utils;

import com.onevizion.onevizionchallenge.models.Book;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ComparatorUtils {

    public static Map<String, Comparator<Book>> comparators = new HashMap<>();

    static {
        comparators.put(Book.Fields.title, Comparator.comparing(Book::getTitle).reversed());
        comparators.put(Book.Fields.author, Comparator.comparing(Book::getAuthor).reversed());
        comparators.put(Book.Fields.description, Comparator.comparing(Book::getDescription).reversed());
    }


    public static Comparator<Book> getComparator(String key) {
        return Collections.unmodifiableMap(comparators).get(key);
    }
}
