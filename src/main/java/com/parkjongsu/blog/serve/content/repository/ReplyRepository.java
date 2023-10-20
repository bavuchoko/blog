package com.parkjongsu.blog.serve.content.repository;

import com.parkjongsu.blog.serve.content.entity.Content;
import com.parkjongsu.blog.serve.content.entity.Reply;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyRepository {

    @PersistenceContext
    private EntityManager entityManager;
    public List<Reply> findRecentLimitedTo(int limit) {
        return entityManager
                .createQuery("SELECT r FROM Reply r ORDER BY r.createDate", Reply.class)
                .setMaxResults(limit).getResultList();
    }
}
