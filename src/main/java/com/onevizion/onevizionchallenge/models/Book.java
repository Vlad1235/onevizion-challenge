package com.onevizion.onevizionchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@Builder
@FieldNameConstants
public class Book {

    private int id;
    private String title;
    private String author;
    private String description;

}
