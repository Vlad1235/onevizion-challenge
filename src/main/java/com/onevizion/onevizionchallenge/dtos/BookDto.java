package com.onevizion.onevizionchallenge.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class BookDto {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    private String description;
}
