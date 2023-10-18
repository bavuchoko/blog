package com.parkjongsu.blog.serve.statistics.visitcount.repository;

import com.parkjongsu.blog.serve.statistics.visitcount.entity.VisitCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface VisitCountJpaRepository extends JpaRepository<VisitCount, LocalDate> {
}
