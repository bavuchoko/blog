package com.parkjongsu.blog.content.repository;

import com.parkjongsu.blog.content.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentJpaRepository extends JpaRepository<Content, Long> {

}
