package uees.project.geo.jobs;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ClearCacheInMemory {

    @Scheduled(fixedRate = 30 * 60 * 1000) // 30 minutos en milisegundos
    public void cleanExpiredEntries() {
        this.clearCache();
    }

    @CacheEvict("extremeEvents")
    public void clearCache() {

    }


}
