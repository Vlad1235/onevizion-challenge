package com.onevizion.onevizionchallenge.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SqlConstants {

    public static final String SQL_QUERY_SELECT_ALL = "select id, title, author, description from book";
    public static final String SQL_QUERY_SELECT_ONE = "select id, title, author, description from book where id = ?";
    public static final String SQL_QUERY_INSERT_ONE = "insert into book(id, title, author, description) values (?, ?, ?, ?)";
    public static final String SQL_QUERY_DELETE_ONE = "delete from employees where id = ?";

}
