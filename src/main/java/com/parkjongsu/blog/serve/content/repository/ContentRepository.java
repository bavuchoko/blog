package com.parkjongsu.blog.serve.content.repository;

import com.parkjongsu.blog.serve.content.entity.Content;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Content> findRecentLimitedTo(int limit) {
        return entityManager
                .createQuery("SELECT c FROM Content  c ORDER BY c.createDate", Content.class)
                .setMaxResults(limit).getResultList();
    }
}
