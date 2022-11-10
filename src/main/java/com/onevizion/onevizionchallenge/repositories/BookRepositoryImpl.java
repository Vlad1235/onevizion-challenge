package com.onevizion.onevizionchallenge.repositories;

import com.onevizion.onevizionchallenge.exceptions.CustomSQLErrorCodeTranslator;
import com.onevizion.onevizionchallenge.models.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.onevizion.onevizionchallenge.utils.SqlConstants.SQL_QUERY_DELETE_ONE;
import static com.onevizion.onevizionchallenge.utils.SqlConstants.SQL_QUERY_INSERT_ONE;
import static com.onevizion.onevizionchallenge.utils.SqlConstants.SQL_QUERY_SELECT_ALL;
import static com.onevizion.onevizionchallenge.utils.SqlConstants.SQL_QUERY_SELECT_ONE;

@Slf4j
@Repository
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.setExceptionTranslator(new CustomSQLErrorCodeTranslator());
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(SQL_QUERY_SELECT_ALL, this::mapRowToBook);
    }

    @Override
    public Book findOne(Integer id) {
        return jdbcTemplate.queryForObject(SQL_QUERY_SELECT_ONE, this::mapRowToBook, id);
    }

    @Override
    public void save(Book book) {
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(SQL_QUERY_INSERT_ONE, new String[]{"id"});
            stmt.setInt(1, book.getId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getDescription());
            return stmt;
        });
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(SQL_QUERY_DELETE_ONE, new String[]{"id"});
            stmt.setInt(1, id);
            return stmt;
        });
    }

    private Book mapRowToBook(ResultSet resultSet, int rowNum) throws SQLException {
        return Book.builder()
                .id(resultSet.getInt(Book.Fields.id))
                .title(resultSet.getString(Book.Fields.title))
                .author(resultSet.getString(Book.Fields.author))
                .description(resultSet.getString(Book.Fields.description))
                .build();
    }
}
