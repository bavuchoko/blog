package com.parkjongsu.blog.serve.statistics.visitcount.service.impl;

import com.parkjongsu.blog.serve.statistics.visitcount.repository.VisitCountJpaRepository;
import com.parkjongsu.blog.serve.statistics.visitcount.service.VisitCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitCountServiceImpl implements VisitCountService {

    private final VisitCountJpaRepository visitCountJpaRepository;

    @Override
    public void visitCountToDb(List list) {
        visitCountJpaRepository.saveAll(list);
    }
}
