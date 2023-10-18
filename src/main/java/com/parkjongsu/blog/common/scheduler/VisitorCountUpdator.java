package com.parkjongsu.blog.common.scheduler;

import com.parkjongsu.blog.config.utils.RedisUtil;
import com.parkjongsu.blog.serve.statistics.visitcount.repository.VisitCountJpaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class VisitorCountUpdator {

    private final VisitCountJpaRepository visitCountJpaRepository;
    private final String SHOULD_BACKUP;
    private final RedisUtil REDISUTILS;

    public VisitorCountUpdator(
        @Value("${globals.shouldBackup}") String shouldBackup,
        RedisUtil redisUtil,
        VisitCountJpaRepository visitCountJpaRepository
    ) {
        this.SHOULD_BACKUP = shouldBackup;
        this.REDISUTILS = redisUtil;
        this.visitCountJpaRepository = visitCountJpaRepository;
    }

    @Async
    @Scheduled(cron = "${globals.updateDatabase}")
    public void updateDatabase() {
        if (StringUtils.hasText(SHOULD_BACKUP) && "Y".equals(SHOULD_BACKUP)) {
            String keyPattern = "20\\d\\d-\\d\\d-\\d\\d";
            List list = REDISUTILS.moveDataFromRedisToDb(keyPattern);
            visitCountJpaRepository.saveAll(list);
        }
    }

}
