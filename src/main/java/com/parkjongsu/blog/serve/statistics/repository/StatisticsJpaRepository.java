package com.parkjongsu.blog.serve.statistics.repository;

import com.parkjongsu.blog.serve.statistics.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface StatisticsJpaRepository extends JpaRepository<Statistics, LocalDate> {
}
