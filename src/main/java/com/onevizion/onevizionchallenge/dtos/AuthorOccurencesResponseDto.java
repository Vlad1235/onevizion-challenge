package com.onevizion.onevizionchallenge.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorOccurencesResponseDto {
    private String author;
    private int occurrence;
}
