package com.parkjongsu.blog.serve.files.repository;

import com.parkjongsu.blog.serve.files.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesJpaRepository extends JpaRepository<Files, Long> {
}
