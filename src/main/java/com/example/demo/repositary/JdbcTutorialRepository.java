package com.example.demo.repositary;

import com.example.demo.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@Primary
public class JdbcTutorialRepository implements TutorialRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    @NonNull
    public Tutorial save(Tutorial tutorial) {
        String sql = "INSERT INTO tutorial (title,description,published) VALUES(?,?,?) RETURNING " +
                "id,title,description";
        return Objects.requireNonNull(
                this.jdbcTemplate.queryForObject(
                        sql,
                        new BeanPropertyRowMapper<>(Tutorial.class),
                        tutorial.getTitle(), tutorial.getDescription(),
                        tutorial.isPublished()
                ), "Failed to create Tutorial"
        );
        
    }
    
    
    @Override
    public Tutorial update(Tutorial tutorial) {
        String query = "UPDATE tutorial SET title = ?, description = ?, published = ? WHERE id = " +
                "? RETURNING id, title, description";
        return jdbcTemplate.queryForObject(
                query, new BeanPropertyRowMapper<>(Tutorial.class),
                tutorial.getTitle(),
                tutorial.getDescription(),
                tutorial.isPublished(),
                tutorial.getId()
        );
    }
    
    @Override
    public Tutorial findById(UUID id) {
        try {
            return this.jdbcTemplate.queryForObject(
                    "SELECT * FROM tutorial WHERE id = ?",
                    BeanPropertyRowMapper.newInstance(Tutorial.class), id
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
    
    
    public void deleteById(UUID id) {
        jdbcTemplate.update("DELETE FROM tutorial WHERE id=?", id);
    }
    
    @Override
    @NonNull
    public List<Tutorial> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM tutorial",
                BeanPropertyRowMapper.newInstance(Tutorial.class)
        );
    }
    
    @Override
    public List<Tutorial> findByPublished(boolean published) {
        return jdbcTemplate.query(
                "SELECT * from tutorial WHERE published=?",
                BeanPropertyRowMapper.newInstance(Tutorial.class), published
        );
    }
    
    @Override
    public List<Tutorial> findByTitleContaining(String title) {
        String query = "SELECT * FROM tutorial WHERE title LIKE %" + title + "%";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Tutorial.class));
    }
    
    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE from tutorials");
    }
}
