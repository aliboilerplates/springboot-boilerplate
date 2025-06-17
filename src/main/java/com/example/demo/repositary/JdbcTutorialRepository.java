package com.example.demo.repositary;

import com.example.demo.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class JdbcTutorialRepository implements TutorialRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public int save(Tutorial tutorial) {
        return this.jdbcTemplate.update(
                "INSERT INTO tutorial (title,description,published) VALUES(?,?," +
                        "?)", new Object[]{
                        tutorial.getTitle(), tutorial.getDescription(),
                        tutorial.isPublished()
                }
        );
    }
    
    @Override
    public int update(Tutorial tutorial) {
        String query = "UPDATE tutorial SET title = ?, description = ?, published = ? WHERE id = ?";
        return jdbcTemplate.update(
                query, new Object[]{
                        tutorial.getTitle(),
                        tutorial.getDescription(),
                        tutorial.isPublished(),
                        tutorial.getId()
                }
        );
    }
    
    @Override
    public Tutorial findById(Long id) {
        try {
            return this.jdbcTemplate.queryForObject(
                    "SELECT * FROM tutorial WHERE id = ?",
                    BeanPropertyRowMapper.newInstance(Tutorial.class), id
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM tutorial WHERE id=?", id);
    }
    
    @Override
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
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from tutorials");
    }
}
