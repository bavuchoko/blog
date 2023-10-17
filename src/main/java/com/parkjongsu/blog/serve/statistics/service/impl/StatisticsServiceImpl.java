package com.parkjongsu.blog.serve.statistics.service.impl;

import com.parkjongsu.blog.serve.statistics.repository.StatisticsJpaRepository;
import com.parkjongsu.blog.serve.statistics.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsJpaRepository statisticsJpaRepository;

    @Override
    public void visitCountToDb(List list) {
        statisticsJpaRepository.saveAll(list);
    }
}
